import requests
# f=open('college.csv','w',)
for px in range(1,147):
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
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36 Edg/125.0.0.0',
        'sec-ch-ua': '"Microsoft Edge";v="125", "Chromium";v="125", "Not.A/Brand";v="24"',
        'sec-ch-ua-mobile': '?0',
        'sec-ch-ua-platform': '"Windows"',
    }

    json_data = {
        'keyword': '',
        'page': px,
        'province_id': '',
        'ranktype': '',
        'request_type': 1,
        'signsafe': 'f10119677fd8e0a639de8cd4da1e1db3',
        'size': 20,
        'top_school_id': '[3117]',
        'type': '',
        'uri': 'apidata/api/gkv3/school/lists',
    }

    response = requests.post(
        f'https://api.zjzw.cn/web/api/?keyword=&page={px}&province_id=&ranktype=&request_type=1&size=20&top_school_id=[3117]&type=&uri=apidata/api/gkv3/school/lists&signsafe=f10119677fd8e0a639de8cd4da1e1db3',
        headers=headers,
        json=json_data,
    ).json()

    # Note: json_data will not be serialized by requests
    # exactly as it was in the original request.
    #data = '{"keyword":"","page":1,"province_id":"","ranktype":"","request_type":1,"signsafe":"f10119677fd8e0a639de8cd4da1e1db3","size":20,"top_school_id":"[3117]","type":"","uri":"apidata/api/gkv3/school/lists"}'
    #response = requests.post(
    #    'https://api.zjzw.cn/web/api/?keyword=&page=1&province_id=&ranktype=&request_type=1&size=20&top_school_id=[3117]&type=&uri=apidata/api/gkv3/school/lists&signsafe=f10119677fd8e0a639de8cd4da1e1db3',
    #    headers=headers,
    #    data=data,
    #)
    # res=[]
    for i in response['data']['item']:
        li=[]
        li.append(i['city_id'])#市id
        li.append(i['city_name'])#市名称
        li.append(i['county_id'])#县id
        li.append(i['county_name'])#县名称
        li.append(str(i['f211']))#是否为211
        li.append(str(i['f985']))#是否为985
        li.append(i['level_name']);#本科/专科
        li.append(i['school_id'])#学校id
        li.append(i['name'])#学校名称
        li.append(i['nature_name'])#办学类型：民办/公办...
        li.append(i['province_id'])#省份id
        li.append(i['province_name'])#省份名称
        li.append(i['type_name'])#院校类型
        li.append(i['dual_class_name'])#双一流
        # res.append(li)
        with open('college.csv', 'a+') as f:
            f.write(','.join(map(str, li))+'\n')
    # print(res)
    f.close()