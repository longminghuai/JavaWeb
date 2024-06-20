# import requests
# import csv
# import time
# import logging

# logging.basicConfig(filename='scraper.log', level=logging.INFO)

# headers = {
#     'Accept': 'application/json',
#     'Content-Type': 'application/json;charset=utf-8',
#     'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.124 Safari/537.36 Edg/102.0.1245.44'
# }

# def get_detail(year, sid):
#     lst = []
#     retries = 3
#     for attempt in range(retries):
#         try:
#             url = f"https://static-data.gaokao.cn/www/2.0/schoolspecialscore/{sid}/{year}/37.json"
#             print(url)
#             response = requests.request("GET", url, headers=headers, timeout=5)
#             response.raise_for_status()
#             data = response.json().get('data', {})
#             if not data:
#                 logging.warning(f"No data found for {url}")
#                 return lst
#             num = int(data.get('3_1570_0', {}).get('numFound', 0))
#             lst += data.get('3_1570_0', {}).get('item', [])
#             if num <= attempt * 10:
#                 break
#         except requests.RequestException as e:
#             logging.error(f"Request failed (attempt {attempt + 1}/{retries}): {e}")
#             time.sleep(2)  # 等待2秒后重试
#             continue
#         break  # 如果请求成功，跳出循环
#     else:
#         logging.error(f"Failed to get data for {sid} after {retries} attempts.")
#     return lst

# def get_school_list(province_id="", is_985="", is_211="", is_dual_class=""):
#     page_size = 30
#     lst_sch = []
#     for i in range(1, 2000):
#         url = f"https://api.eol.cn/web/api/?&keyword=&page={i}&uri=apidata/api/gk/school/lists&size={page_size}&province_id={province_id}"
#         print(url)
#         try:
#             response = requests.request("GET", url, headers=headers, timeout=5)
#             response.raise_for_status()
#             data = response.json()
#             num = int(data['data']['numFound'])
#             # print(data)
#             for item in data['data']['item']:
#                 lst_sch.append([item['school_id'], str(item['code_enroll']), item['name'],
#                                item['province_name'], item['city_name']])
#             if i * page_size >= num:
#                 break
#         except requests.RequestException as e:
#             logging.error(f"Request failed for school list page {i}: {e}")
#             break
#     return lst_sch

# def main():
#     save_file = open('result2.csv', 'w', newline='', encoding='utf-8')
#     output = csv.writer(save_file)
#     output.writerow(['年份', '省份', '院校代码', '学校', '学科门类', '专业类',
#                     '专业名称', '最低分', '最低位次', '选科要求', '城市', '地址', '录取批次', '网址'])
#     lst_sch = get_school_list(province_id="37")
#     for sid, scode, sname, province, city in lst_sch:
#         print(sid, scode[:-2], sname, province, city)
#         for year in range(2023, 2022, -1):
#             data = get_detail(year=year, sid=sid)
#             for item in data:
#                 tmp = [year, province, scode[:-2], sname, item['level2_name'], item['level3_name'], item['spname'],
#                        item['min'], item['min_section'],
#                        item['local_type_name'], city, item['local_batch_name'],
#                        f'https://www.gaokao.cn/school/{sid}/provinceline']
#                 output.writerow(tmp)
#     save_file.close()

# if __name__ == '__main__':
#     main()


import requests
import csv
import time
import logging

logging.basicConfig(filename='scraper.log', level=logging.INFO)

headers = {
    'Accept': 'application/json',
    'Content-Type': 'application/json;charset=utf-8',
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.124 Safari/537.36 Edg/102.0.1245.44'
}

def get_detail(year, sid, province_code):
    lst = []
    retries = 3
    for attempt in range(retries):
        try:
            
    # https://static-data.gaokao.cn/www/2.0/schoolspecialplan/42/2023/43.json
            url = f"https://static-data.gaokao.cn/www/2.0/schoolspecialscore/{sid}/{year}/{province_code}.json"
            print(url)
            response = requests.request("GET", url, headers=headers, timeout=5)
            response.raise_for_status()
            data = response.json().get('data', {})
            if not data:
                logging.warning(f"No data found for {url}")
                return lst
            for key, value in data.items():
                if 'item' in value:
                    lst += value['item']
            break
        except requests.RequestException as e:
            logging.error(f"Request failed (attempt {attempt + 1}/{retries}): {e}")
            time.sleep(2)  # 等待2秒后重试
            continue
    else:
        logging.error(f"Failed to get data for {sid} after {retries} attempts.")
    return lst

def get_school_list(province_id="", is_985="", is_211="", is_dual_class=""):
    page_size = 30
    lst_sch = []
    for i in range(1, 2000):
        url = f"https://api.eol.cn/web/api/?&keyword=&page={i}&uri=apidata/api/gk/school/lists&size={page_size}&province_id={province_id}"
        print(url)
        try:
            response = requests.request("GET", url, headers=headers, timeout=5)
            response.raise_for_status()
            data = response.json()
            num = int(data['data']['numFound'])
            for item in data['data']['item']:
                lst_sch.append([item['school_id'], str(item['code_enroll']), item['name'],
                               item['province_name'], item['city_name']])
            if i * page_size >= num:
                break
        except requests.RequestException as e:
            logging.error(f"Request failed for school list page {i}: {e}")
            break
    return lst_sch

def main():
    save_file = open('result2.csv', 'w', newline='', encoding='utf-8')
    output = csv.writer(save_file)
    output.writerow(['年份', '省份', '院校代码', '学校', '学科门类', '专业类',
                    '专业名称', '最低分', '最低位次', '选科要求', '城市', '地址', '录取批次', '网址'])
    for i in range(11,66):
        province_code = str(i) 
        lst_sch = get_school_list(province_id=province_code)
        for sid, scode, sname, province, city in lst_sch:
            print(sid, scode[:-2], sname, province, city)
            for year in range(2023, 2022, -1):
                data = get_detail(year=year, sid=sid, province_code=province_code)
                for item in data:
                    print(item)
                    tmp = [year, province, scode[:-2], sname, item.get('level2_name', ''), item.get('level3_name', ''), item.get('spname', ''),
                        item.get('min', ''), item.get('min_section', ''),
                        item.get('local_type_name', ''), city, item.get('local_batch_name', ''),
                        f'https://www.gaokao.cn/school/{sid}/provinceline']
                    output.writerow(tmp)
                    print(tmp)

if __name__ == '__main__':
    main()


# import requests
# import csv
# import time
# import logging

# logging.basicConfig(filename='scraper.log', level=logging.INFO)

# headers = {
#     'Accept': 'application/json',
#     'Content-Type': 'application/json;charset=utf-8',
#     'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.124 Safari/537.36 Edg/102.0.1245.44'
# }

# def get_data(url):
#     retries = 3
#     for attempt in range(retries):
#         try:
#             response = requests.get(url, headers=headers, timeout=5)
#             response.raise_for_status()
#             return response.json().get('data', {})
#         except requests.RequestException as e:
#             logging.error(f"Request failed (attempt {attempt + 1}/{retries}): {e}")
#             time.sleep(2)
#             continue
#     logging.error(f"Failed to get data from {url} after {retries} attempts.")
#     return {}

# def get_detail(year, sid, province_code):
#     plan_url = f"https://static-data.gaokao.cn/www/2.0/schoolspecialplan/{sid}/{year}/{province_code}.json"
#     score_url = f"https://static-data.gaokao.cn/www/2.0/schoolspecialscore/{sid}/{year}/{province_code}.json"
    
#     plan_data = get_data(plan_url)
#     score_data = get_data(score_url)
    
#     combined_data = []
    
#     plan_items = [item for sublist in plan_data.values() for item in sublist.get('item', [])]
#     score_items = [item for sublist in score_data.values() for item in sublist.get('item', [])]
#     # print(plan_items)
#     print("-----------------------------------------------------")
#     print(score_items)
#     score_dict = {(item['special_id'], item['batch']): item for item in score_items}
    
#     for plan_item in plan_items:
#         key = (plan_item['special_id'], plan_item['batch'])
#         score_item = score_dict.get(key, {})
#         combined_item = {**plan_item, **score_item}
#         combined_data.append(combined_item)
    
#     return combined_data

# def get_school_list(province_id="", is_985="", is_211="", is_dual_class=""):
#     page_size = 30
#     lst_sch = []
#     for i in range(1, 2000):
#         url = f"https://api.eol.cn/web/api/?&keyword=&page={i}&uri=apidata/api/gk/school/lists&size={page_size}&province_id={province_id}"
#         print(url)
#         try:
#             response = requests.get(url, headers=headers, timeout=5)
#             response.raise_for_status()
#             data = response.json()
#             num = int(data['data']['numFound'])
#             for item in data['data']['item']:
#                 lst_sch.append([item['school_id'], str(item['code_enroll']), item['name'],
#                                item['province_name'], item['city_name']])
#             if i * page_size >= num:
#                 break
#         except requests.RequestException as e:
#             logging.error(f"Request failed for school list page {i}: {e}")
#             break
#     return lst_sch

# def main():
#     save_file = open('result2.csv', 'w', newline='', encoding='utf-8')
#     output = csv.writer(save_file)
#     output.writerow(['年份', '省份', '院校代码', '学校', '学科门类', '专业类',
#                     '专业名称', '最低分', '最低位次', '选科要求', '城市', '地址', '录取批次', '网址'])
#     for i in range(11, 66):
#         province_code = str(i)
#         lst_sch = get_school_list(province_id=province_code)
#         for sid, scode, sname, province, city in lst_sch:
#             print(sid, scode[:-2], sname, province, city)
#             for year in range(2023, 2022, -1):
#                 data = get_detail(year=year, sid=sid, province_code=province_code)
#                 for item in data:
#                     tmp = [year, province, scode[:-2], sname, item.get('level2_name', ''), item.get('level3_name', ''), item.get('spname', ''),
#                         item.get('min', ''), item.get('min_section', ''),
#                         item.get('sg_name', ''), city, item.get('local_batch_name', ''),
#                         f'https://www.gaokao.cn/school/{sid}/provinceline']
#                     output.writerow(tmp)
#                     # print(tmp)
#     save_file.close()

# if __name__ == '__main__':
#     main()
