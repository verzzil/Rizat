const express = require('express');
const app = express();

app.use(express.static('public'));
app.listen(80);
console.log("Server started at port 80");