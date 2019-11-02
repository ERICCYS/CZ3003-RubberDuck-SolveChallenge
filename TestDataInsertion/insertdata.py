import requests
import json

def insert_students():
    data = [
        {
            "firstName": "Xiao",
            "lastName": "Ma",
            "userName": "MAXI0009",
            "password": "MAXI0008!",
            "mark": 0
        },
        {
            "firstName": "Xiaoo",
            "lastName": "Maa",
            "userName": "MAXI0007",
            "password": "MAXI0007!",
            "mark": 0
        },
        {
            "firstName": "Eric",
            "lastName": "Ma",
            "userName": "MAXI0006",
            "password": "MAXI0006!",
            "mark": 0
        },
        {
            "firstName": "Ericc",
            "lastName": "Maa",
            "userName": "MAXI0005",
            "password": "MAXI0005!",
            "mark": 0
        },{
            "firstName": "Eric",
            "lastName": "Ma",
            "userName": "MAXI0004",
            "password": "MAXI0004!",
            "mark": 0
        }
    ]
    for datum in data:
        # depends on your server
        r = requests.post("http://localhost:8082/api/student", json=datum)
        print("##############")
        print(r.json())
        print("##############")

def insert_teachers():
    data = [
        {
            "firstName": "Eric",
            "lastName": "Ma",
            "userName": "MAXIAO",
            "password": "MAXIAO123456!",
            "title": "Professor"
        },
    ]
    for datum in data:
        # depends on your server
        r = requests.post("http://localhost:8082/api/teacher", json=datum)
        print("##############")
        print(r.json())
        print("##############")

def insert_questions():
    characters = [
        "product_manager",
        "lead_developer", 
        "quality_manager"
    ]
    phrases = [
        "1_requirement_gathering_and_analysis_I",
        "1_requirement_gathering_and_analysis_II",
        "1_requirement_gathering_and_analysis_III",
        "2_design_I",
        "2_design_II",
        "2_design_II",
        "3_implement_I",
        "3_implement_II",
        "3_implement_III",
        "4_testing_and_deployment_I",
        "4_testing_and_deployment_II",
        "4_testing_and_deployment_III",
        "5_maintenance_I",
        "5_maintenance_II",
        "5_maintenance_III"
    ]

    headers = {'Authorization': 'TJhjh9+Ww8bmtS21WgaelDmEWSN7ckjDj3VUBAZ3Vp54gq96odaF7fki7M0uRkqZllmPQjsHVgdILXAYlmaXMV7lBj2kcztrhTAOIYORsT2EroYWEi9UxAOtKH6WLOJa'}

    for character in characters:
        for phrase in phrases:
            with open('test_data/{}/{}_{}.json'.format(character,character,phrase), 'r') as f:
                data = json.load(f)
                for datum in data:
                    r = requests.post("http://localhost:8082/api/question", json=datum, headers=headers)
                    print("##############")
                    print(r.json())
                    print("##############")

    # with open('product_manager_design_III.json', 'r') as f:
    #     data = json.load(f)
    #     print(type(data))

    # Need to put the correct 'Authroization' here to do the authentication

    

# insert_students()
# insert_teachers()
insert_questions()
