$(function() {
    console.log('Loaded...');
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