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

### Student Sign in

Method: GET

URL: "http://localhost:9090/api/student/signin"

Request Header: None

Request Param:
```json
{
  "username": "<student's username>",
  "password": "<student's password>"
}
```

Path Variable: None

Request Body: None

Response: The result of the authentication and access token of this student if auth is correct


### Student View Profile (Mark and Progress)

Method: GET

URL: "http://localhost:9090/api/student/"

Request Header: 
```json
{
  "Authorization" : "<student access token>"
}
```

Request Param: None

Path Variable: None

Request Body: None

Respone: The full information related to this student


### Teacher Sign In

Method: GET

URL: "http://localhost:9090/api/teacher/signin"

Request Header: None

Request Param:
```json
{
  "username": "<teacher's username>",
  "password": "<teacher's password>"
}
```

Path Variable: None

Request Body: None

Response: The result of the authentication and access token of this teacher if auth is correct


### Teacher View Profile 

Method: GET

URL: "http://localhost:9090/api/teacher/"

Request Header: 
```json
{
  "Authorization" : "<teacher access token>"
}
```

Request Param: None

Path Variable: None

Request Body: None

Respone: The full information related to this teacher


### World Initializing

Method: GET

URL: "http://localhost:9090/api/world/"

Request Header:
```json
{
  "Authorization" : "<user access token>"
}
```

Request Param: None

Path Variable: None

Request Body: None

Response: The progress (i.e. which part is locked and which part is unlocked)


### Fetch A Question 

Method: GET

URL: "http://localhost:9090/api/question/"

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
  "character": "character",
  "section": "section",
  "level": "level",
  "world": "world"
}
```

Response: The question that is in this level


### Student Answer Question

Method: POST

URL: "http://localhost:9090/api/answer/create"

Request Header:
```json
{
  "Authorization" : "<user access token>"
}
```

Request Param:
```json
{
  "studentID": "<studentID>",
  "questionID": "<questionID>"
}
```

Path Variable: None

Request Body:
```json
{
  "answer": "answer",
  "attempts": "attempts"
}
```
Response: The result of this answer and the point the student gets


### View Leader Board

Method: GET

URL: "http://localhost:9090/api/leaderboard"

Request Header:
```json
{
  "Authorization" : "<student access token>"
}
```

Request Param: None

Path Variable: None

Request Body: None

Response: All users' username and their score in sorted order


### Techer Create Question

Method: POST

URL: "http://localhost:9090/api/question/create" 

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
  "questionDescription": "questionDescription",
  "character": "character",
  "topic": "topic",
  "level": "level",
  "difficulty": "difficulty",
  "choiceA": "choiceAContent",
  "choiceB": "choiceBContent",
  "choiceC": "choiceCContent",
  "choiceD": "choiceDContent",
  "correctChoice": "correctChoice",
  "currentUserLevel": "currentUserLevel"
}
```

Response: ACK


### Student Create Challenge

Method: POST

URL: "http://localhost:9090/api/challenge/create"

Request Header:
```json
{
  "Authorization" : "<student access token>"
}
```

Request Param: None

Path Variable: None

Request Body: 
```
{
  "questionCount": questionCount,
  "easyQns": easyQns,
  "mediumQns": mediumQns,
  "hardQns": hardQns,
  "currentUserLevel": currentUserLevel
}
```

Response: ACK


### Student Take Challenge

Method: POST

URL: "http://localhost:9090/api/challenge/take"

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


### Teacher Check Performance


### Teacher Check Progress

