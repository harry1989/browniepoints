$(function() {
	console.log('Loaded...');
	setLocation();
	loadQuestions();
	setupUser();
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
	console.log(html);
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

function submitAnswer(qid) {
	console.log('Entering submitAnswer');
	var uid = 1;
	var ansid = 1;
	var anstext = '';

	for ( var i = 1; i <= 4; i++) {
		var el = '#lradio' + i + '_' + qid;
		if ($(el + ' span').hasClass('checked')) {
			anstext = $(el).text().trim();
			break;
		}
	}
	if (anstext) {
		$.ajax({
			type : "POST",
			url : '/a',
			dataType : 'json',
			data : 'uid=' + uid + '&qid=' + qid + '&a=' + anstext,
			success : function(response) {
				handleAnswer(response);
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert(xhr.responseText);
				alert(thrownError);
			}
		});
	}
}

function handleAnswer(response) {
	console.log('In handleAnswer...');
	var source = $('#answer-template').html();
	var template = Handlebars.compile(source);

	if (response.answer_status === 'Y') {
		response.header = '<span class="y-correct">That\'s correct!</span>';
		response.correct = 1;
	} else {
		response.header = '<span class="y-incorrect">Sorry that was incorrect!</span>';
		response.correct = 0;
	}

	var html = template(response);
	$('#answer').html(html);
	$('#answer > div').foundation('reveal', 'open');

}

function shuffleQuestions() {
	console.log('In shuffleQuestions...');
	var uid = 1;

	$.get('/q?uid=' + uid, function(data) {
		questions = data;
		loadQuestions();
	});

}

function addQuestion() {
	var title = $('#aqTitle').val();
	var desc = $('#aqDesc').val();
	var trivia = $('#aqTrivia').val();
	var furl = $('#aqUrl').val();
	var option1 = $('#aqOption1').val();
	var option2 = $('#aqOption2').val();
	var option3 = $('#aqOption3').val();
	var option4 = $('#aqOption4').val();
	var a = $('#aqAnswer').val().replace(' ', '');
	var answer = $('#aq' + a).val();
	var cuisine = $('#aqCuisine').val().toLowerCase();
	var name = $('#aqHotelName').val();
	var uid = 1;

	var url = 'title=' + encodeURIComponent(title);
	url += '&desc=' + encodeURIComponent(desc);
	url += '&trivia=' + encodeURIComponent(trivia);
	url += '&url=' + encodeURIComponent(furl);
	url += '&option1=' + encodeURIComponent(option1);
	url += '&option2=' + encodeURIComponent(option2);
	url += '&option3=' + encodeURIComponent(option3);
	url += '&option4=' + encodeURIComponent(option4);
	url += '&answer=' + encodeURIComponent(answer);
	url += '&cuisine=' + encodeURIComponent(cuisine);
	url += '&name=' + encodeURIComponent(name);
	url += '&uid=' + uid;

	console.log(url);
	$.post('/aq', url, function() {
		console.log('Added!');
	});

}

function setupUser() {
	if(user_email && user_email != 'null') {
		$('#signInLink').text(user_email);
		$('#signInLink').attr('href', '/p');
		$('#signInLink').parent().removeAttr('data-reveal-id');
	}
}
