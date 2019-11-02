# RubberDuck Web Service API
Eric Ma - CZ3003 RubberDuck

## Gerneral Information

`FE and BE`
- Q: How the frontend connect to the backend?
- A: Through RESTful API

`RESTful API`
- Q: What is RESTful API?
- A: (short answer) a form of API featuring HTTP request including **GET** **POST** **PUT** **DELETE**

`FE call BE`
- Q: How the frontend call the backend?
- A: Through HTTP Request (We can use **okhttp**)

`HTTP Request`
- Q: What is inside a HTTP Request?
- A: Include:
  1. Request Method
  2. Request URL
  3. Request Header
  4. Request Parameter
  5. Path Variable
  6. Request Body

`About this doc`
- Q: What this document will provide?
- A: HTTP Request for every function provided here.

`Server IP: 206.189.151.97`

### Student Sign in

Method: GET

URL: "http://206.189.151.97:8082/api/student/signin"

Request Header: None

Request Param:
```json
{
  "userName": "<student's userName>",
  "password": "<student's password>"
}
```

Path Variable: None

Request Body: None

Response: The result of the authentication and access token of this student if auth is correct


### Student View Profile (Mark and Progress)

Method: GET

URL: "http://206.189.151.97:8082/api/student"

Request Header: None

Request Param:
```json
{
  "id": "<student's id>"
}
```

Path Variable: None

Request Body: None

Respone: The full information related to this student


### Teacher Sign In

Method: GET

URL: "http://206.189.151.97:8082/api/teacher/signin"

Request Header: None

Request Param:
```json
{
  "userName": "<teacher's userName>",
  "password": "<teacher's password>"
}
```

Path Variable: None

Request Body: None

Response: The result of the authentication and access token of this teacher if auth is correct


### Teacher View Profile 

Method: GET

URL: "http://206.189.151.97:8082/api/teacher"

Request Header: None

Request Param: 
```json
{
  "id": "<teacher's id>"
}
```

Path Variable: None

Request Body: None

Respone: The full information related to this teacher


### World Initializing

Method: GET

URL: "http://206.189.151.97:8082/api/world/initialize"

Request Header:


Request Param: 
```json
{
  "studentId" : "<student ID>",
  "character": "<character>"
}
```

Path Variable: None

Request Body: None

Response: The progress (i.e. which part is locked and which part is unlocked)

### Fetch A Question By ID

Method: GET

URL: "http://206.189.151.97:8082/api/question"

Request Header: None


Request Param: 
```json
{
  "id" : "<question ID>"
}
```

Path Variable: None

Request Body: None

Response: The question that match this ID


### Fetch Questions By Stage

Method: GET

URL: "http://206.189.151.97:8082/api//question/stage"

Request Header: None

Request Param: 
```json
{
  "character": "<character>",
  "section": "<section>",
  "level": "<level>",
  "world": "<world>"
}
```

Path Variable: None

Request Body: None

Response: The questions that is in this level

### Techer Create Question

Method: POST

URL: "http://206.189.151.97:8082/api//question/stage"

Request Header: 
```json
{
  "Authorization" : "<teacher access token>"
}

Request Param: None

Path Variable: None

Request Body: 
```json
{
  "description" : "<question description>",
  "character" : "<question character>",
  "world" : "<question world>",
  "section" : "<question section>",
  "level" : "<question level: EASY/MEDIUM/HARD>",
  "difficulty" : "<question difficulty: EASY/MEDIUM/HARD>",
  "choiceA" : "<choice A content>",
  "choiceB" : "<choice B content>",
  "choiceC" : "<choice C content>",
  "choiceD" : "<choice D content>",
  "correctChoice" : "<correct choice A/B/C/D>",
  "award" : 15,
  "bonus" : false
}
```

Response: The question created

### Student Answer Question

Method: POST

URL: "http://206.189.151.97:8082/api/answer"

Request Header:
```json
{
  "Authorization" : "<user access token>"
}
```

Request Param: None

Path Variable: None

Request Body:
```json
{
  "studentId" : "<student ID>",
  "questionId" : "<question ID>",
  "choice" : "<choice>",
  "correct" : "false",
  "reward" : "0",
  "mode" : "<question answer mode: C/Q>"
}
```
Response: The answer response after the validation, the system will update the "is correct" and the reward

### View Leader Board

Method: GET

URL: "http://206.189.151.97:8082/api/leaderboard"

Request Header: None

Request Param: None

Path Variable: None

Request Body: None

Response: All users with their score in sorted order


### Student Create Challenge

Method: POST

URL: "http://206.189.151.97:8082/api/challenge"

Request Header:
```json
{
  "Authorization" : "<student access token>"
}
```

Request Param: None

Path Variable: None

Request Body: 
```json
{
  "creatorId": 1,
  "character": "Product Manager",
  "questionIds": [],
  "worldQuestion": [
    {
      "world": "Design",
      "count": 7
    },
    {
      "world": "Implementation or Coding",
      "count": 4
    }
  ],
  "createTime": null,
  "successCount": 0,
  "failureCount": 0
}
```

Response: A list of questions sampled by the system that satisfy the requirement of the challeng


### Student Take Challenge

Method: POST

URL: "http://206.189.151.97:8082/api/challenge/take"

Request Header:
```json
{
  "Authorization" : "<student access token>"
}
```

Request Param:
```json
{
  "studnetID": "studentID",
  "challengeID": "challengeID"
}
```

Path Variable: None

Request Body: 
```json
{
  "correctAnswerCount" : "correctAnswerCount"
}
```

Response: If the user win the challenge and the point he/she gets from this challenge


### Teacher Check Question Performance

Method: GET

URL: "http://206.189.151.97:8082/api/statistic/section"

Request Header: None

Request Param: 
```json
{
  "character": "<character>"
}
```

Path Variable: None

Request Body: None

Response: Performances of every question

### Teacher Check Section Performance

Method: GET

URL: "http://206.189.151.97:8082/api/statistic/section"

Request Header: None

Request Param: 
```json
{
  "character": "<character>"
}
```

Path Variable: None

Request Body: None

Response: Performances of every section

### Teacher Check World Performance

Method: GET

URL: "http://206.189.151.97:8082/api/statistic/world"

Request Header: None

Request Param: 
```json
{
  "character": "<character>"
}
```

Path Variable: None

Request Body: None

Response: Performances of every world
