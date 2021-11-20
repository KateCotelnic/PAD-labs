const express = require('express');
const router = express.Router();
const postDriver = require('../models/PostDriver');
const postPas = require('../models/PostPassenger');
const postTrip = require('../models/PostTrip');

//ROUTES
router.post('/', (req, res) => {
    console.log(req.body);
});

router.post('/postPas', (req, res) => {
    const post = new postPas({
        pasID: req.body.pasID,
        firstname: req.body.firstname,
        lastname: req.body.lastname,
        card: req.body.card,
        points: req.body.points,
        phone: req.body.phone,
    });

    post.save()
    .then(data => {
        res.json(data);
    })
    .catch(err => {
        res.json({ message: err });
    })
});

router.post('/postDriver', (req, res) => {
    const post = new postDriver({
        driverID: req.body.driverID,
        firstname: req.body.firstname,
        lastname: req.body.lastname,
        car_nr: req.body.car_nr,
        color: req.body.color,
        points: req.body.points,
        phone: req.body.phone,
    });

    post.save()
    .then(data => {
        res.json(data);
    })
    .catch(err => {
        res.json({ message: err });
    })
});

router.post('/postTrip', (req, res) => {
    const post = new postTrip({
        tripID: req.body.tripID,
        location: req.body.location,
        destination: req.body.destination,
        type: req.body.type,
        pasID: req.body.pasID,
        driverID: req.body.driverID,
        paymentType: req.body.paymentType,
        tripTime: req.body.tripTime,
        costs: req.body.costs,
        tips: req.body.tips,
        date: req.body.date,
    });

    post.save()
    .then(data => {
        res.json(data);
    })
    .catch(err => {
        res.json({ message: err });
    })
});

module.exports = router;