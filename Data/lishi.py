import requests
import json

# 定义URL
url = "https://static-data.gaokao.cn/www/2.0/section2021/2023/43/2074/3/lists.json"

# 发送GET请求
response = requests.get(url)

# 检查响应状态码
if response.status_code == 200:
    # 解析响应的JSON数据
    data = response.json()
    
    # 获取'search'部分的数据
    search_data = data['data']['search']
    
    # 初始化结果列表
    result = []

    # 遍历search数据的键值对
    i=1
    for key, value in search_data.items():
        temp = []
        temp.append(str(i))
        temp.append(value['score'])
        temp.append(value['num'])
        result.append(temp)
        i+=1
        with open('lishi.csv', 'a+') as f:
            f.write(','.join(map(str, temp))+'\n')
    # print(res)
    f.close()
    
    # 打印结果
    for res in result:
        print(res)
else:
    print(f"请求失败，状态码：{response.status_code}")
