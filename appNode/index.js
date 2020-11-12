const express = require("express")
const axios = require("axios")


const PORT = process.env.PORT || 4200
const app = express();

var bodyParser = require('body-parser');
var urlencodedParser = bodyParser.urlencoded({ extended: false })

app.use(express.static('public'));

app.get("/", (req, res) => {
  res.send("Welcome to your App!")
})

app.get('/index.htm', function (req, res) {
   res.sendFile( __dirname + "/" + "index.html" );
})

app.get('/process_get', function (req, res) {
   // Prepare output in JSON format
   response = {
      first_name:req.query.first_name,
      last_name:req.query.last_name
   };
   console.log(response);
   res.end(JSON.stringify(response));
})
app.get('/indexpost.htm', function (req, res) {
   res.sendFile( __dirname + "/" + "indexpost.html" );
})

app.post('/process_post', urlencodedParser, function (req, res) {
   // Prepare output in JSON format
   response = {
      first_name:req.body.first_name,
      last_name:req.body.last_name
   };
   console.log(response);
   res.end(JSON.stringify(response));
})

app.get("/getData", (req, res) => {
  axios.get("https://jsonplaceholder.typicode.com/posts")
    .then(function(response) {
      res.json(response.data)
    }).catch(function(error) {
      res.json("Error occured!")
    })
})

app.post("/getUserById", (req, res) => {
  if (!req.body.id) {
    res.json("No ID found in reqest body.")
  } else {
    axios.get(`https://jsonplaceholder.typicode.com/users/${req.body.id}`)
      .then(function(response) {
        res.json(response.data)
      }).catch(function(error) {
        res.json("Error occured!")
      })
  }
})

app.listen(PORT, function () {
  console.log(`Express server listening on port ${PORT}`)
})