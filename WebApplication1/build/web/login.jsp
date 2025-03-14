<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login | EduOnline</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/login.css">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</head>
<body>

<%@ include file="header.jsp" %>

<div class="login-container" id="loginContainer">
    <!-- Sign In Form -->
    <div class="form-container sign-in-container">
        <div class="form">
            <h2 class="text-center fw-bold">Log in</h2>
<!--            <p class="text-center">
                <a href="signupstudent.jsp" class="text-center-student">Sign up as a student</a>
                <a href="signuptutor.jsp" class="text-center-student">Sign up as a tutor</a>
            </p>-->
            <div class="social-login">
                <button class="btn btn-outline-dark w-100 d-flex align-items-center justify-content-center">
                    <i class="fab fa-google me-2"></i> Continue with Google
                </button>
            </div>
            <div class="separator d-flex align-items-center my-3">
                <hr class="flex-grow-1">
                <span class="mx-2 text-or">or</span>
                <hr class="flex-grow-1">
            </div>
            <form action="login" method="post" id="loginForm">
                <div class="mb-3 text-start">
                    <label>Email</label>
                    <input type="email" class="form-control" name="email" placeholder="Your email" required>
                </div>
                <div class="mb-3 text-start">
                    <label>Password</label>
                    <div class="input-group">
                        <input type="password" class="form-control" name="password" id="loginPassword" placeholder="Your password" required>
                        <div class="input-group-text password-toggle" onclick="togglePassword('loginPassword')">
                            <i class="fas fa-eye"></i>
                        </div>
                    </div>
                </div>
                <div class="d-flex justify-content-between align-items-center">
                    <a href="forgotpassword.jsp" class="text-decoration-none">Forgot your password?</a>
                </div>
                <div class="form-check my-3 text-start">
                    <input type="checkbox" class="form-check-input" id="rememberMe">
                    <label class="form-check-label" for="rememberMe">Remember me</label>
                </div>
                <button type="submit" class="btn btn-primary w-100">Log in</button>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger mt-3">${error}</div>
                </c:if>
            </form>
        </div>
    </div>

    <!-- Sign Up Form -->
    <div class="form-container sign-up-container">
        <div class="form">
            <h2 class="text-center fw-bold">Sign Up</h2>
            
            <p class="text-center">
                Already have an account? <a href="#" onclick="toggleSignUp()">Log in</a>
            </p>
            <div class="social-login">
                <button class="btn btn-outline-dark w-100 d-flex align-items-center justify-content-center">
                    <i class="fab fa-google me-2"></i> Continue with Google
                </button>
            </div>
            <div class="separator d-flex align-items-center my-3">
                <hr class="flex-grow-1">
                <span class="mx-2 text-or">or</span>
                <hr class="flex-grow-1">
            </div> 
            <p class="text-center">
                <a href="signupstudent.jsp" class="text-center-student">Sign up as a student</a>
                <a href="signuptutor.jsp" class="text-center-student">Sign up as a tutor</a>
            </p>
            <form action="registerServlet" method="post">
                <div class="mb-3 text-start">
                    <label>Full Name</label>
                    <input type="text" class="form-control" name="fullname" placeholder="Your full name" required>
                </div>
                <div class="mb-3 text-start">
                    <label>Email</label>
                    <input type="email" class="form-control" name="email" placeholder="Your email" required>
                </div>

                <div class="mb-3 text-start">
                    <label>Password</label>
                    <div class="input-group">
                        <input type="password" class="form-control" name="password" placeholder="Your password" required>
                        <div class="input-group-text password-toggle">
                            <i class="fas fa-eye"></i>
                        </div>
                    </div>
                </div>

<!--                <div class="d-flex justify-content-between align-items-center">
                    <a href="#" class="forgot-password">Forgot your password?</a>
                </div>-->

                <button type="submit" class="btn-login">Sign Up</button>
            </form>
        </div>
    </div>

    <!-- Overlay Panel -->
    <div class="overlay-container" id="overlayContainer">
        <div class="overlay">
            <div class="text-wrapper">
                <h2 id="overlayTitle">Hello, Friend!</h2>
                <p id="overlayText">Enter your details and start your journey with us.</p>
                <button class="btn btn-outline-light" onclick="toggleSignUp()">Sign Up</button>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>

<script>
    function toggleSignUp() {
        document.getElementById("loginContainer").classList.toggle("right-panel-active");
        if (document.getElementById("loginContainer").classList.contains("right-panel-active")) {
            document.getElementById("overlayTitle").innerText = "Welcome Back!";
            document.getElementById("overlayText").innerText = "If you already have an account, log in now.";
        } else {
            document.getElementById("overlayTitle").innerText = "Hello, Friend!";
            document.getElementById("overlayText").innerText = "Enter your details and start your journey with us.";
        }
    }

    function togglePassword(fieldId) {
        let input = document.getElementById(fieldId);
        let icon = input.nextElementSibling.querySelector("i");
        if (input.type === "password") {
            input.type = "text";
            icon.classList.replace("fa-eye", "fa-eye-slash");
        } else {
            input.type = "password";
            icon.classList.replace("fa-eye-slash", "fa-eye");
        }
    }
</script>
<script src="resources/script/jquery-3.7.1.min.js"></script>

</body>
</html>
