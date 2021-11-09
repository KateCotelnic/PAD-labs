const express = require('express');

const postDriver = require('../models/PostDriver');
const postPas = require('../models/PostPassenger');
const postTrip = require('../models/PostTrip');
const router = express.Router();

//ROUTES
router.get('/getPassenger', async (req, res) => {
  try{
    const passengers = await postPas.find();
    res.json(passengers);
  }catch(err){
    res.join({message:err});
  }
});

//GET Passenger BY ID
// router.get('/:pasID', async (req, res) => {
//   try{
//     const passengers = await postPas.findById(req.params.pasID);
//     res.json(passengers);
//   }catch(err){
//     res.json({message:err});
//   }
// });

router.get('/getDriver', async (req, res) => {
  try{
    const drivers = await postDriver.find();
    res.json(drivers);
  }catch(err){
    res.join({message:err});
  }
});

//GET DRIVER BY ID
// router.get('/:drivID', async (req, res) => {
//   try{
//     const drivers = await postDriver.findById(req.params.driverID);
//     res.json(drivers);
//   }catch(err){
//     res.json({message:err});
//   }
// });

router.get('/getTrip', async (req, res) => {
  try{
    const trips = await postTrip.find();
    res.json(trips);
  }catch(err){
    res.join({message:err});
  }
});

//GET TRIP BY ID
// router.get('/:tripID', async (req, res) => {
//   try{
//     const trips = await postTrip.findById(req.params.tripID);
//     res.json(trips);
//   }catch(err){
//     res.json({message:err});
//   }
// });

module.exports = router;