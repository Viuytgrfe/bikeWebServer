let coords = JSON.parse(httpGet("/rest/lastPosition"))
console.log(coords)

let mapOptions = {
    center: [coords.latitude, coords.longitude],
    zoom: 10
}

let map = new L.map('map' , mapOptions);

let layer = new L.TileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png');
map.addLayer(layer);

let marker = new L.Marker([coords.latitude, coords.longitude]);
marker.addTo(map);
/*
var lineJson = { "type": "FeatureCollection", "features": [{ "type": "Feature", "properties": {}, "geometry": { "type": "LineString", "coordinates": [[82.5732421875, 29.233683670282787], [84.4573974609375, 28.401064827220896], [82.4139404296875, 28.28019589809702]] } }] }

var lineData = L.geoJSON(lineJson).addTo(map)*/

document.getElementById("coords").innerText = coords.latitude + ", " + coords.longitude

//document.getElementById("debug").innerText = "Debug data:\n" + JSON.stringify(coords, undefined, 3)
function httpGet(theUrl)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false );
    xmlHttp.send( null );
    return xmlHttp.responseText;
}



var pointA = new L.LatLng(28.635308, 77.22496);
var pointB = new L.LatLng(28.984461, 77.70641);
var pointList = [pointA, pointB];

var firstpolyline = new L.Polyline(pointList, {
    color: 'red',
    weight: 3,
    opacity: 0.5,
    smoothFactor: 1
});
firstpolyline.addTo(map);