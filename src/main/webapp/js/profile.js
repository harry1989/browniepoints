﻿$(function() {
    console.log('Loaded...');
    loadProfile();
    showAnswers();
    showQuestions();
    
});

function showAnswers() {
  var ansHtml = "";
  var container = $('<div/>');
  var list = $('<ul />');
  list.addClass('ans-item');
  list.addClass('small-block-grid-2');
  list.addClass('large-block-grid-4');

  for(var i = 0; i < 12; i++) {
    var item = $('<li />');
    var img = $('<img/>');
    img.attr('href', '#.');
    var url = 'http://placehold.it/150x150';
    img.attr('src', url);
    item.append(img);
    list.append(item);
  }
  container.append(list);
  ansHtml = container.html();
  $('#my_answers').html(ansHtml);
}

function showQuestions() {
  var qnsHtml= "";
  var container = $('<div/>');
  var list = $('<ul />');
  list.addClass('ans-item');
  list.addClass('small-block-grid-2');
  list.addClass('large-block-grid-4');

  for(var i = 0; i < 8; i++) {
    var item = $('<li />');
    var img = $('<img/>');
    img.attr('href', '#.');
    var url = 'http://placehold.it/150x150';
    img.attr('src', url);
    item.append(img);
    list.append(item);
  }
  container.append(list);
  qnsHtml= container.html();
  $('#my_questions').html(qnsHtml);
}




function getRandomInt (min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

function loadProfile() {
	console.log('In loadProfile..');
	if(!user_json) {
		alert('OOPS: No user info!');
	}
	$('#pUserName').val(user_json.user.email);
	$('#pFullName').val(user_json.user.name);
	$('#pEmail').val(user_json.user.email);
	
}

function saveProfile() {
	console.log('In saveProfile..');
	$.post('/p', 'name=' + $('#pFullName').val() + '&phone=' + $('#pCell').val(), function() {
		console.log('Saved!');
	})
}