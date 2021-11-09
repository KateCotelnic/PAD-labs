const mongoose = require('mongoose');

const PassengerSchema = mongoose.Schema({
  pasID: String,
  firstname: String,
  lastname: String,
  card: String,
  points: String,
  phone: String,
},
{ collection : 'Passengers' });

module.exports = mongoose.model('PostPassenger', PassengerSchema)