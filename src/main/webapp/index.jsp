

<%-- 
    Document   : index
    Created on : 2016. 7. 14, 오전 2:34:42
    Author     : heungjae
--%>

<%@page language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
        <%
            HttpSession sessionID = request.getSession();
            String sessionHash = sessionID.toString();
        %>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Landing Page</title>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/user.css">

        <script>
            $(document).ready(function () {
                $("#goButton").click(makeRequest);
            });

            function makeRequest() {
                // Define properties
                var AUTH_ENDPOINT = "https://sandbox-apigw.koscom.co.kr/auth/oauth/v2/authorize";
                var RESPONSE_TYPE = "code";
                var CLIENT_ID = "l7xxf234248b6fbd42a1a6844861524b2320";
                var REDIRECT_URI = "http://localhost:8084/callbacknew";
                var SCOPE = "test";
                var STATE = "<%=sessionHash%>";

                // Build authorization request endpoint
                var requestEndpoint = AUTH_ENDPOINT + "?" +
                        "response_type=" + encodeURIComponent(RESPONSE_TYPE) + "&" +
                        "client_id=" + encodeURIComponent(CLIENT_ID) + "&" +
                        "redirect_uri=" + encodeURIComponent(REDIRECT_URI) + "&" +
                        "scope=" + encodeURIComponent(SCOPE) + "&" +
                        "state=" + encodeURIComponent(STATE);

                // Send to authorization request endpoint
                window.location.href = requestEndpoint;
            }
        </script>
    </head>

    <body>
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header"><a class="navbar-brand navbar-link" href="#"><i class="glyphicon glyphicon-phone"></i>Mobile App</a>
                    <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
                </div>
                <div class="collapse navbar-collapse" id="navcol-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="active" role="presentation"><a href="#">First Item</a></li>
                        <li role="presentation"><a href="#">Second Item</a></li>
                        <li role="presentation"><a href="#">Third Item</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="jumbotron hero">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 col-md-push-7 phone-preview">
                        <div class="iphone-mockup"><img src="assets/img/iphone.svg" class="device">
                            <div class="screen"></div>

                        </div>
                    </div>
                    <div class="col-md-6 col-md-pull-3 get-it">
                        <h1>OpenAPI Test Site</h1>   
                        <p>KOSCOM.</p>
                        <div class="input-group">
                            <span class="input-group-addon" id="username">@</span>
                            <input type="text" class="form-control" placeholder="사용자ID" aria-describedby="basic-addon1">
                        </div>
                        <!-- <a href="query.html"> -->

                        <!--</a>-->
                        
                        <p> <button id = "goButton" class="btn btn-info"  type="button">GO! </button></p>
                    </div>
                    <div class="col-md-12 col-md-offset-5"></div>
                </div>
            </div>
        </div>
       
        <section class="features">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <h2>자본시장 오픈플랫폼 테스트용 앱</h2>
                        <p>코스콤 기술연구소</p>
                    </div>
                    <div class="col-md-6">
                        <div class="row icon-features">
                            <div class="col-xs-4 icon-feature"><i class="glyphicon glyphicon-signal"></i>
                                <p>Grow Your Wealth</p>
                            </div>
                            <div class="col-xs-4 icon-feature"><i class="glyphicon glyphicon-piggy-bank"></i>
                                <p>Saves You Money</p>
                            </div>
                            <div class="col-xs-4 icon-feature"><i class="glyphicon glyphicon-music"></i>
                                <p>Happy Life</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <footer class="site-footer">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <h5>Mobile App © 2016</h5></div>
                    <div class="col-sm-6 social-icons"><a href="#"><i class="fa fa-facebook"></i></a><a href="#"><i class="fa fa-twitter"></i></a><a href="#"><i class="fa fa-instagram"></i></a></div>
                </div>
            </div>
        </footer>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    </body>

</html>
