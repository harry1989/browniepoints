$(function() {
    console.log('Loaded...');
    //setLocation();
});


function setLocation() {
    navigator.geolocation.getCurrentPosition(function(loc) { 
        var lat = loc.coords.latitude;
        var lon = loc.coords.longitude;        
        var query = 'select * from geo.placefinder where text="' + lat + ',' + lon + '" and gflags="R" limit 1';
        $.YQL(query, function(data) {
            var city = data.query.results.Result.city.toUpperCase(); 
            $('.y-geo').html(city);
        });
    })
}

$.YQL = function(query, callback) { 
    if (!query || !callback) {
        throw new Error('$.YQL(): Parameters may be undefined');
    } 
    var encodedQuery = encodeURIComponent(query.toLowerCase());
    var url = 'http://query.yahooapis.com/v1/public/yql?q=' + encodedQuery + '&format=json&callback=?';
    $.getJSON(url, callback); 
};
 
