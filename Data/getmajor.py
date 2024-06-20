import requests
import pandas as pd
num=[1,2,230]
datalist=[]
for i in num:
    for px in range(1,50):
        headers = {
            'Accept': 'application/json, text/plain, */*',
            'Accept-Language': 'zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6',
            'Connection': 'keep-alive',
            'Content-Type': 'application/json',
            'Origin': 'https://www.gaokao.cn',
            'Referer': 'https://www.gaokao.cn/',
            'Sec-Fetch-Dest': 'empty',
            'Sec-Fetch-Mode': 'cors',
            'Sec-Fetch-Site': 'cross-site',
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0',
            'sec-ch-ua': '"Not/A)Brand";v="8", "Chromium";v="126", "Microsoft Edge";v="126"',
            'sec-ch-ua-mobile': '?0',
            'sec-ch-ua-platform': '"Windows"',
        }

        json_data = {
            'keyword': '',
            'level1': '1',
            'level2': '',
            'level3': '',
            'page': 1,
            'signsafe': '845895dae545d9d5c9101b71bcfd4b0c',
            'size': 30,
            'sort': '',
            'uri': 'apidata/api/gkv3/special/lists',
        }
        
        response = requests.post(
            f'https://api.zjzw.cn/web/api/?keyword=&level1={i}&level2=&level3=&page={px}&size=30&sort=&uri=apidata/api/gkv3/special/lists&signsafe=845895dae545d9d5c9101b71bcfd4b0c',
            headers=headers,
            json=json_data,
        ).json()
        
        # Note: json_data will not be serialized by requests
        # exactly as it was in the original request.
        #data = '{"keyword":"","level1":"1","level2":"","level3":"","page":1,"signsafe":"845895dae545d9d5c9101b71bcfd4b0c","size":30,"sort":"","uri":"apidata/api/gkv3/special/lists"}'
        #response = requests.post(
        #    'https://api.zjzw.cn/web/api/?keyword=&level1=1&level2=&level3=&page=1&size=30&sort=&uri=apidata/api/gkv3/special/lists&signsafe=845895dae545d9d5c9101b71bcfd4b0c',
        #    headers=headers,
        #    data=data,
        #)
        if response is not None and 'data' in response and isinstance(response['data'], dict) and 'item' in response['data']:
            for j in response['data']['item']:
                temp=[]
                temp.append(j['boy_rate'])
                temp.append(j['degree'])
                temp.append(j['fivesalaryavg'])
                temp.append(j['girl_rate'])
                temp.append(j['level1_name'])
                temp.append(j['level2_name'])
                temp.append(j['level3_name'])
                temp.append(j['name'])
                temp.append(j['limit_year'])
                datalist.append(temp)
                df = pd.DataFrame(datalist, columns=['boy_rate', 'degree', 'fivesalaryavg', 'girl_rate', 'level1_name', 'level2_name','level3_name','name','limit_year'])
                df.to_excel('newmajor.xlsx', index=False)
                #    f.write(','.join(map(str, temp))+'\n')
#     # print(res)
# f.close()

            
    
