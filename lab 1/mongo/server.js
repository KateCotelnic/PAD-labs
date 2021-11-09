const express = require('express');
const app = express();
const mongoose = require('mongoose');
const bodyParser = require('body-parser');

app.use(bodyParser.json()); 

//IMPORTS
const getRoutes = require('./api/routes/gets');
const postRoutes = require('./api/routes/posts');

app.use('/get', getRoutes);

app.use('/post', postRoutes);

//CONNECT TO DB
mongoose.connect('mongodb://localhost:27017/mydb', () =>
  console.log('Connected to mongo')
);

//LISTEN TO PORT
app.listen(3000);
