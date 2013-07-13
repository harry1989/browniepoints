$(function() {
	console.log('Loaded...');
	setLocation();
	loadQuestions();
});

function setLocation() {
	navigator.geolocation.getCurrentPosition(function(loc) {
		var lat = loc.coords.latitude;
		var lon = loc.coords.longitude;
		var query = 'select * from geo.placefinder where text="' + lat + ','
				+ lon + '" and gflags="R" limit 1';
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
	var url = 'http://query.yahooapis.com/v1/public/yql?q=' + encodedQuery
			+ '&format=json&callback=?';
	$.getJSON(url, callback);
};

function loadQuestions() {

	var questions = [ {
		qid : 1,
		url : 'http://placehold.it/400x400&text=question1',
		title : 'Guess the hotel!',
		coupon_title : 'Win a 50% coupon!',
		desc : 'This is a desc 1'
	}, {
		qid : 2,
		url : 'http://placehold.it/400x400&text=question2',
		title : 'Guess the hotel!',
		coupon_title : 'Win a 50% coupon!',
		desc : 'This is a desc 2'
	}, {
		qid : 3,
		url : 'http://placehold.it/400x400&text=question3',
		title : 'Guess the dish!',
		coupon_title : 'Win a 50% coupon!',
		desc : 'This is a desc 3'
	} ];

	// Ensure we have some questions
	if (!questions) {
		alert("OOPS: Could not find questions");
	}

	var source = $('#question-template').html();
	var template = Handlebars.compile(source);
	var html = '';

	for ( var i = 0; i < questions.length; i++) {
		html += template(questions[i]);
	}
	$('#home-questions').html(html);

	source = $('#full-question-template').html();
	template = Handlebars.compile(source);
	html = '';

	for ( var i = 0; i < questions.length; i++) {
		html += template(questions[i])
	}
	$('#full-questions').html(html);
	console.log(html);
}
