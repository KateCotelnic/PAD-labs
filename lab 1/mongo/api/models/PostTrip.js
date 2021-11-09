const mongoose = require('mongoose');

const TripSchema = mongoose.Schema({
  tripID: String,
  location: String,
  destination: String,
  type: String,
  pasID: String,
  driverID: String,
  paymentType: String,
  tripTime: String,
  costs: String,
  tips: String,
  date: String,
},
{ collection : 'trips' });

module.exports = mongoose.model('PostTrip', TripSchema)