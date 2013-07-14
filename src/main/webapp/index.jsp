<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width" />
    <title>Welcome to Brownie Points</title>
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/foundation.css">
    <link rel="stylesheet" href="css/app.css">
    <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,400italic|Open+Sans:400italic,400|Tangerine:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="foundation_icons_all/foundation_icons_social/stylesheets/social_foundicons.css" />
    <script src="js/vendor/custom.modernizr.js"></script>  
	<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
	<script type="text/javascript" src="js/handlebars.js"></script> 
	
	<script>
		var questions = <%= request.getAttribute("questions") %>
	</script>
	    
  </head>
  <body>
    <!-- Header and Nav -->
    <div class="row">&nbsp;</div>
    <div class="row">
      <div class="large-4 columns">
        <img src="img/logo.png" alt="Logo" />
      </div>
      <div class="large-2 columns">
        <form class="custom">
        <select id="cuisineDropdown" class="medium">
            <option value="All" selected>Any Cuisine</option>
            <option value="Chinese">Chinese</option>
            <option value="Continental">Continental</option>
            <option value="Indian">Indian</option>
            <option value="Italian">Italian</option>              
        </select>
        </form>
      </div>
      <div class="large-2 columns"> 
        <form class="custom">
        <select id="cuisineDropdown" class="medium">
            <option selected>Within 50 kms</option>
            <option>Within 5 kms</option>
            <option>Within 15 kms</option>
            <option>Within 35 kms</option>              
        </select>
        </form>
      </div>
      <div class="large-4 columns y-topright">
        <ul class="inline-list right">
          <li><a href="#" class="y-geo">HYDERABAD</a></li>
          <li data-reveal-id="addQuestion"><a href="#" class="y-cap">ADD QUESTION</a></li>
          <li data-reveal-id="signIn"><a href="profile.html" class="y-cap">SIGN IN</a></li>
        </ul>
      </div>
    </div>

    <!-- spaaaaace -->    
    <div class="row">&nbsp;</div>
    <div class="row">&nbsp;</div>
    <div class="row">&nbsp;</div>

	
    <!-- Main 3 questions -->	
    <div class="row" id="home-questions">

    </div>
    
    <!-- Shuffle -->
    <div class="row">&nbsp;</div>
    <div class="row text-center">
      <a href="javascript:shuffleQuestions();" title="Shuffle this question set" class="button small success y-shuffle">SHUFFLE</a>    
    </div>
    
    <!-- Footer -->
  
    <footer class="row">
      <div class="large-12 columns">
        <hr />
        <div class="row">
          <div class="large-3 columns">
            <p class="y-shuffle">&copy; The Nallagandla Debuggers</p>
          </div>
          <div class="large-9 columns">
            <ul class="inline-list right">
              <li>
                <div class="row">
                  <a title="Like on Facebook"target="_blank" 
                    href="http://www.facebook.com/sharer/sharer.php?u=http://bonappetit.com" 
                    class="foundicon-facebook y-social">&nbsp;&nbsp;&nbsp;</a>
                  <a title="+1 on Google+"target="_blank" 
                    href="https://plusone.google.com/_/+1/confirm?hl=en&url=http://bonappetit.com" 
                    class="foundicon-google-plus y-social">&nbsp;&nbsp;&nbsp;</a>                  
                </div>
              </li>
              <li data-reveal-id="howItWorks"><a href="#." class="y-cap">HOW IT WORKS</a></li>
              <li><a href="#" class="y-cap">ABOUT</a></li>
            </ul>
          </div>
        </div>
      </div>
    </footer>
    
    
    <!-- Reveal dialogs -->
    <div id="full-questions">

    </div>
    
    <!-- Any Answer -->
    <div id="answer">
    
    </div>
    
    <!-- How It Works -->
    <div id="howItWorks" class="reveal-modal small">
        <ul data-orbit>
          <li>
            <img alt="step1" src="img/food/step1r.jpg" />
            <div class="orbit-caption">Guess the main ingredient in the dish you see</div>
          </li>
          <li>
            <img alt="step1" src="img/food/step2r.jpg" />
            <div class="orbit-caption">Or the name of the dish itself</div>
          </li>
          <li>
            <img alt="step1" src="img/food/step3r.jpg" />
            <div class="orbit-caption">Or the name of the restaurant shown</div>
          </li>
          <li>
            <img alt="step2" src="img/food/step4r.jpg" />
            <div class="orbit-caption">And win great offers! Discover new cuisines, and explore new restaurants!</div>
          </li>
        </ul>      
    </div>
    
    <!-- Sign In -->
    <div id="signIn" class="reveal-modal tiny">
      <ul class="pricing-table">
        <li class="price y-cap">CHOOSE A SERVICE</li>
        <li class="bullet-item"><a href="/login?op=Yahoo"><img alt="Yahoo" src="img/login_yahoo.png"></a></li>
        <li class="bullet-item"><a href="/login?op=Google"><img alt="Google" src="img/login_google.png"></a></li>
      </ul>
    </div>
    
    <!-- Add Question -->
    <div id="addQuestion"class="reveal-modal large">
      <h6>ENTER QUESTION DETAILS:</h6>
      <hr/>
      <div class="row">&nbsp;</div>
      <form class="custom">
        <div class="row">
          <div class="large-2 columns text-right">
            <label class="inline">FULL QUESTION</label>
          </div>
          <div class="large-10 columns">
            <textarea id="aqDesc" placeholder="Enter the description of your question here"></textarea>
          </div>
        </div>
        <div class="row">
          <div class="large-2 columns text-right">
            <label class="inline">TYPE</label>
          </div>
          <div class="large-4 columns">
            <select id="aqTitle" class="large-6 columns">
                <option selected="selected">Guess the dish</option>
                <option>Guess the ingredients</option>
                <option>Guess the place</option>
            </select>
          </div>
          <div class="large-2 columns text-right">
            <label class="inline">OPTION 1</label>
          </div>
          <div class="large-4 columns">
            <input id="aqOption1" type="text" placeholder="">
          </div>
        </div>
        <div class="row">
          <div class="large-2 columns text-right">
            <label class="inline">IMAGE</label>
          </div>
          <div class="large-4 columns">
            <div class="row collapse">
              <div class="large-6 columns">
                <input type="text" id="aqUrl" placeholder="Upload Image">  
              </div> 
              <div class="large-6 columns">
                <a href="#" class="button postfix">CHOOSE...</a>  
              </div> 
            </div>            
          </div>
          <div class="large-2 columns text-right">
            <label class="inline">OPTION 2</label>
          </div>
          <div class="large-4 columns">
            <input type="text" id="aqOption2" placeholder="">
          </div>
        </div>     
        <div class="row">
          <div class="large-2 columns text-right">
            <label class="inline">HOTEL NAME</label>
          </div>
          <div class="large-4 columns">
            <input type="text" id="aqHotelName" placeholder="Start typing to autocomplete...">
          </div>
          <div class="large-2 columns text-right">
            <label class="inline">OPTION 3</label>
          </div>
          <div class="large-4 columns">
            <input type="text" id="aqOption3" placeholder="">
          </div>
        </div> 
        <div class="row">
          <div class="large-2 columns text-right">
            <label class="inline">CORRECT ANSWER</label>
          </div>
          <div class="large-4 columns">
            <select id="aqAnswer" class="large-6 columns">
                <option selected="selected">Option 1</option>
                <option>Option 2</option>
                <option>Option 3</option>
                <option>Option 4</option>
            </select>
          </div>
          <div class="large-2 columns text-right">
            <label class="inline">OPTION 4</label>
          </div>
          <div class="large-4 columns">
            <input type="text" id="aqOption4" placeholder="">
          </div>
        </div>  
        <div class="row">
          <div class="large-2 columns text-right">
            <label class="inline">CUISINE</label>
          </div>
          <div class="large-4 columns">
            <select id="aqCuisine" class="large-6 columns">
                <option selected="selected">Multi</option>
                <option>Asian</option>
                <option>Continental</option>
                <option>Italian</option>
            </select>
          </div>
          <div class="large-2 columns text-right">
            <label class="inline">TRIVIA</label>
          </div>
          <div class="large-4 columns">
            <input type="text" id="aqTrivia" placeholder="">
          </div>
        </div>  
        <hr/>
        <div class="row">
          <span class="y-cap">All fields are mandatory. Questions will be approved by moderators before being added.</span>
          <ul class="inline-list right">
            <li><a href="javascript:addQuestion();" class="button success small">SUBMIT</a></li>
          </ul>
          <a class="close-reveal-modal">&#215;</a>
        </div>                                                                                                                   
      </form>
    </div>

    <!-- ************************************* -->
    <!--  ALL CLIENT SIDE HANDLEBARS TEMPLATES --> 
    <!-- ************************************* -->

    <script id="question-template" type="text/x-handlebars-template">
      <div class="large-4 columns" data-reveal-id="question{{q.qid}}">
        <img alt="Question Picture" src="{{q.url}}" />
        <div class="panel">
          <h6>{{q.title}}</h6>
          <p class="subheader"><span class="y-hint">{{c.title}}</span></p>
        </div>
      </div>
    </script> 
    
    <script id="full-question-template" type="text/handlebars-template">
      <div id="question{{q.qid}}" class="reveal-modal large">
        <div class="row">
          <h3>{{title}}</h3>
        </div>
        <div class="large-6 columns">
          <img alt="pic3" src="{{q.url}}" />
        </div>
        <div class="large-6 columns">
          <div class="row">
            <div class="panel">
              <div class="y-hint">{{q.desc}}</div>
              <br/><br/>
              <form class="custom">
                <label for="radio1" id="lradio1_{{q.qid}}">
                <input name="radio1" type="radio" id="radio1" style="display:none;">
                <span class="custom radio"></span> &nbsp;{{q.option1}}
                </label>
                <br/>
                <label for="radio1" id="lradio2_{{q.qid}}">
                <input name="radio1" type="radio" id="radio2" style="display:none;">
                <span class="custom radio"></span> &nbsp;{{q.option2}}
                </label>
                <br/>
                <label for="radio1" id="lradio3_{{q.qid}}">          
                <input name="radio1" type="radio" id="radio3" style="display:none;">
                <span class="custom radio"></span> &nbsp;{{q.option3}}
                </label>
                <br/>
                <label for="radio1" id="lradio4_{{q.qid}}">          
                <input name="radio1" type="radio" id="radio3" style="display:none;">
                <span class="custom radio"></span> &nbsp;{{q.option4}}
                </label>
              </form>
            </div>
          </div>
          <div class="row">
            <div class="panel">{{c.title}}</div>
          </div>
          <div class="row">&nbsp;</div>
          <div class="row">&nbsp;</div>
          
          <div class="row">
              <ul class="inline-list right">
                <li>
                  <div class="row">
                    <a title="Like on Facebook"target="_blank" 
                      href="http://www.facebook.com/sharer/sharer.php?u=http://bonappetit.com" 
                      class="foundicon-facebook y-social">&nbsp;&nbsp;&nbsp;</a>
                    <a title="+1 on Google+"target="_blank" 
                      href="https://plusone.google.com/_/+1/confirm?hl=en&url=http://bonappetit.com" 
                      class="foundicon-google-plus y-social">&nbsp;&nbsp;&nbsp;</a>                  
                  </div>
                </li>              
                <li>
                    <a href="javascript:submitAnswer({{q.qid}});" class="button success small">SUBMIT!</a>
                </li>
              </ul>
          </div>
        </div>
        <a class="close-reveal-modal">&#215;</a>
      </div>
    </script>
    
    <!-- Answer template -->
    <script id="answer-template" type="text/handlebars-template">
        <div id="answer{{q.qid}}" class="reveal-modal medium">
          <div class="row">
            <div class="large-6 columns">
              <img alt="pic3" src="{{q.url}}" />
            </div>
            <div class="large-6 columns">
              <div class="panel">
                {{{header}}}<br/><br/>
                Correct Answer: <b>{{q.answer}}</b><br/><br/>                
                {{#if correct}}
                <b>{{c.desc}}</b> <br/><br/>
                <div>Voucher code: <span class='y-voucher'>{{voucher_code}}</span><br/><br/>
                  A copy of this has been emailed to you.
                </div>
                {{/if}}
              </div>
              {{#if q.trivia}}
              <div class="panel y-hint">
                <b>Trivia</b>: {{q.trivia}}
              </div>
              {{/if}}
              <ul class="inline-list right">
                <li>
                  <div class="row">
                    <a title="Like on Facebook"target="_blank" 
                      href="http://www.facebook.com/sharer/sharer.php?u=http://bonappetit.com" 
                      class="foundicon-facebook y-social">&nbsp;&nbsp;&nbsp;</a>
                    <a title="+1 on Google+"target="_blank" 
                      href="https://plusone.google.com/_/+1/confirm?hl=en&url=http://bonappetit.com" 
                      class="foundicon-google-plus y-social">&nbsp;&nbsp;&nbsp;</a>                  
                  </div>
                </li>
                {{#if correct}}
                <li><a href="#." class="button alert small">UNCLAIM</a></li>
                {{/if}}
                <li><a href="#."class="button small">PRINT</a></li>
              </ul>
            </div>
          </div>
          <a class="close-reveal-modal">&#215;</a>
        </div>
    </script>

    
    <script>
      document.write('<script src=js/vendor/' +
      ('__proto__' in {} ? 'zepto' : 'jquery') +
      '.js><\/script>')
    </script>
    <script src="js/foundation.min.js"></script>
    <script>
      $(document).foundation();      
    </script>
    <script src="js/home.js"></script>
  </body>
</html>