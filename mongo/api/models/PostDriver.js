const mongoose = require('mongoose');

const DriverSchema = mongoose.Schema({
  driverID: String,
  firstname: String,
  lastname: String,
  car_nr: String,
  color: String,
  points: String,
  phone: String,
},
{ collection : 'Drivers' });

module.exports = mongoose.model('PostDriver', DriverSchema)