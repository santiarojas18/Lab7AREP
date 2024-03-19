  function formatJSON(jsonObj) {
                 let htmlOutput = "<ul>";

                 for (let key in jsonObj) {
                     if (jsonObj.hasOwnProperty(key)) {
                         htmlOutput += "<li><strong>" + key + ":</strong> ";

                          if (key === "Poster" && typeof jsonObj[key] === "string") {
                             htmlOutput += "<img src='" + jsonObj[key] + "' alt='Poster'>";
                          } else if (typeof jsonObj[key] === "object") {
                             htmlOutput += formatJSON(jsonObj[key]);
                          } else {
                             htmlOutput += jsonObj[key];
                          }

                     htmlOutput += "</li>";
                     }
                 }

                 htmlOutput += "</ul>";
                 return htmlOutput;
             }

 function loadGetMsg() {
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
            let answer = this.responseText;
            if (answer === "true") {
                window.location.href = "successfullogin.html";
            } else {
                document.getElementById("getrespmsg").innerHTML = "Try Again!!";
            }
    };
    xhttp.open("GET", "/login?email=" + email + "&password=" + password);
    xhttp.send();
 }

  function loadGetUsers() {
     const xhttp = new XMLHttpRequest();
     xhttp.onload = function() {
             try {
                response = JSON.parse(this.responseText);
                const formattedOutput = formatJSON(response);
                document.getElementById("getrespmsg").innerHTML = "<pre>" + formattedOutput + "</pre>";
             } catch (error) {
                document.getElementById("getrespmsg").innerHTML =
                this.responseText;
             }
     };
     xhttp.open("GET", "/users");
     xhttp.send();
  }

  function loadGetUser() {
       let email = document.getElementById("email").value;
       const xhttp = new XMLHttpRequest();
       xhttp.onload = function() {
               try {
                  response = JSON.parse(this.responseText);
                  const formattedOutput = formatJSON(response);
                  document.getElementById("getrespmsg").innerHTML = "<pre>" + formattedOutput + "</pre>";
               } catch (error) {
                  document.getElementById("getrespmsg").innerHTML =
                  this.responseText;
               }
       };
       xhttp.open("GET", "/users/" + email);
       xhttp.send();
    }

 function loadLogOut() {
    window.location.href = "index.html";
 }