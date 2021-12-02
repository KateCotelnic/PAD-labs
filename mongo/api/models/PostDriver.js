const mongoose = require('mongoose');
const DriverSchema = mongoose.Schema({
      driverID: String,
      username: String,
      password: String,
      firstname: String,
      lastname: String,
      car_nr: String,
      color: String,
      points: String,
      phone: String,
    },
    { collection : 'drivers' });
module.exports = mongoose.model('PostDriver', DriverSchema)