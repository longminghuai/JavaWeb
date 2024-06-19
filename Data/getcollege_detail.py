import requests
from requests.exceptions import SSLError
import pandas as pd

def get_data(url, headers):
    for _ in range(5):  # 尝试5次
        try:
            response = requests.get(url, headers=headers)
            if response.status_code == 200:
                return response.json()
            else:
                return None
        except SSLError:
            continue
    return None  # 如果5次尝试后仍然失败，返回None

headers = {
    "Accept": "application/json, text/plain, */*",
    "Referer": "https://www.gaokao.cn/",
    "Sec-Ch-Ua": "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Microsoft Edge\";v=\"126\"",
    "Sec-Ch-Ua-Mobile": "?0",
    "Sec-Ch-Ua-Platform": "\"Windows\"",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0"
}

def main():
    # 基础 URL 模板
    base_url = "https://static-data.gaokao.cn/www/2.0/school/{}/info.json"
    data_list = []
    for i in range(30,3800):
        url = base_url.format(i)
        data = get_data(url, headers)
        if data is not None and 'data' in data and data['data'] is not None:
            temp = []
            temp.append(data['data'].get('school_id', ''))  # 学校编号
            temp.append(data['data'].get('address', ''))  # 地址
            temp.append(data['data'].get('site', ''))  # 官网
            temp.append(data['data'].get('phone', ''))  # 招生电话
            temp.append(data['data'].get('email', ''))  # 邮箱
            temp.append(data['data'].get('content', ''))  # 简介
            data_list.append(temp)
            print(temp)
    df = pd.DataFrame(data_list, columns=['school_id', 'address', 'site', 'phone', 'email', 'content'])
    df.to_excel('college_detail.xlsx', index=False)

if __name__ == "__main__":
    main()
