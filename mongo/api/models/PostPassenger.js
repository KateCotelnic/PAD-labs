const mongoose = require('mongoose');
const PassengerSchema = mongoose.Schema({
      pasID: String,
      username: String,
      password: String,
      firstname: String,
      lastname: String,
      card: String,
      points: String,
      phone: String,
    },
    { collection : 'passangers' });
module.exports = mongoose.model('PostPassenger', PassengerSchema)