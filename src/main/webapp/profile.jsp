<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width" />
    <title>Welcome to Appet-It!</title>
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/foundation.css">
    <link rel="stylesheet" href="css/app.css">
    <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,400italic|Open+Sans:400italic,400|Tangerine:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="foundation_icons_all/foundation_icons_social/stylesheets/social_foundicons.css" />
    <script src="js/vendor/custom.modernizr.js"></script>
    <script>
    	<%= request.getAttribute("uid") %>
        var user_email = '<%= session.getAttribute("email") %>';    	
        var user_json = <%= request.getAttribute("userJson") %>;
        console.log(user_json);
    	
    </script>  
  </head>
  <body>
    <!-- Header and Nav -->
    <div class="row">&nbsp;</div>
    <div class="row y-bar">
      <div class="large-4 columns">
        <img src="img/logo.png" alt="Logo" />
      </div>
      <div class="large-8 columns">
        <ul class="inline-list right">
          <li><a href="#" class="y-cap">ADD QUESTION</a></li>
          <li><a href="/yhack" class="y-cap">HOME</a></li>
        </ul>
      </div>
    </div>
    <!-- End Header and Nav -->
    
    <div class="row">&nbsp;</div>
    <div class="row">&nbsp;</div>
    
    <!-- Main Sections -->
    <div class="row">
    
      <div class="section-container vertical-tabs" data-section="vertical-tabs">
      
        <!-- User Profile Form -->
        <section>
          <p class="title" data-section-title><a href="#">MY ACCOUNT</a></p>
          <div class="content" data-section-content>
            <form class="custom">
              <div class="row">
                <div class="large-4 columns text-right">
                  <label class="inline">USERNAME (required)</label>
                </div>
                <div class="large-6 columns">
                  <input type="text" disabled id='pUserName' placeholder="">
                </div>
                <div class="large-2 columns">&nbsp;</div>
              </div>
              <div class="row">
                <div class="large-4 columns text-right">
                  <label class="inline">FULL NAME (required)</label>
                </div>
                <div class="large-6 columns">
                  <input type="text" id='pFullName' placeholder="">
                </div>
                <div class="large-2 columns">&nbsp;</div>
              </div>                 
              <div class="row">
                <div class="large-4 columns text-right">
                  <label class="inline">EMAIL (required)</label>
                </div>
                <div class="large-6 columns">
                  <input type="text" id='pEmail' placeholder="">
                </div>
                <div class="large-2 columns">&nbsp;</div>
              </div> 
              <div class="row">
                <div class="large-4 columns text-right">
                  <label class="inline">MOBILE NUMBER</label>
                </div>
                <div class="large-6 columns">
                  <input type="text" placeholder="Enter 10 digit mobile number">
                </div>
                <div class="large-2 columns">&nbsp;</div>
              </div>  
              <div class="row">
                <div class="large-4 columns text-right">
                  <label class="inline">SUBSCRIBE TO NEWSLETTER</label>
                </div>
                <div class="large-6 columns">
                  <select id="customDropdown1" class="medium">
                      <option>Never</option>
                      <option>Once Every Week</option>
                      <option selected>Once Every Month</option>
                  </select>
                </div>
                <div class="large-2 columns">&nbsp;</div>
              </div>   
              <div class="row">
                <div class="large-4 columns text-right">
                  <label class="inline">MY POINTS</label>
                </div>
                <div class="large-2 columns">
                  <input disabled type="text" class="text-center y-my-points" value="1200">
                </div>
                <div class="large-2 columns">
                  <a class="button small  y-full-width" href="#.">REDEEM</a>
                </div>                
                <div class="large-4 columns">&nbsp;</div>
              </div> 
              <div class="row">
                <div class="large-4 columns text-right">
                  <label class="inline">SAVE CHANGES</label>
                </div>
                <div class="large-2 columns">
                  <a class="button success small y-full-width" href="javascript:saveProfile();">SUBMIT</a>
                </div>                
                <div class="large-6 columns">&nbsp;</div>
              </div>                                                                                                           
            </form>
          </div>
        </section>
        
        <!-- My Answers -->
        <section>
          <p class="title" data-section-title><a href="#">MY ANSWERS</a></p>
          <div class="content" id="my_answers" data-section-content>
               
          </div>
        </section>
        
        <!-- My Questions -->
        <section>
          <p class="title" data-section-title><a href="#">MY QUESTIONS</a></p>
          <div class="content" id="my_questions" data-section-content>
          
          </div>
        </section>
      </div>
      
    </div>


    <!-- Shuffle -->
    <div class="row">&nbsp;</div>
  
    <!-- Footer -->
    <div class="row">&nbsp;</div>
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
    
    <script>
      document.write('<script src=js/vendor/' +
      ('__proto__' in {} ? 'zepto' : 'jquery') +
      '.js><\/script>')
    </script>
    <script src="js/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>
    <script type="text/javascript" src="js/app.js"></script>
    <script type="text/javascript" src="js/profile.js"></script>
    <script type="text/javascript" src="js/masonry.pkgd.min.js"></script>
  </body>
</html>