let content = document.getElementById('content');
let image = document.getElementById('stateImg');

function renderChecks(result) {
    let newContent = "<table><tr><th>check</th><th>result</th></tr>";
    
    for (var key in result.checks) {
        newContent+= "<tr><td>"+ key + "</td><td>" + result.checks[key] + "</td></tr>";
    }
    newContent += "</table>";
    newContent += "<div>";
    if(result.positive && result.positive.length > 0) {
        newContent += '<div class="positive"><ul>';
        for(let url of result.positive) {
            newContent += "<li>" + url + "</li>";    
        }
        newContent += "</ul></div>";
    }
    if(result.negative && result.negative.length > 0) {
        newContent += '<div class="negative"><ul>';
        for(let url of result.negative) {
            newContent += "<li>" + url + "</li>";    
        }
        newContent += "</ul></div>";
    }
    newContent += "</div>";
    content.innerHTML = newContent;
    image.src="done.png";
}

function renderError(error) {
    content.innerHTML = "<pre>" + error + "</pre>";
    image.src="error.png";
}
function postDataTest(url, data = {}) {
    return fetch(url);
}
function postData(url, data = {}) {
      return fetch(url, {
          method: 'POST',
          mode: 'no-cors',
          cache: 'no-cache',
          headers: {
              'Content-Type': 'application/json',
          },
          redirect: 'follow',
          referrer: 'no-referrer',
          body: JSON.stringify(data),
      });
}

function sendRequest(result) {
    image.src="loading.gif";
    postData('http://localhost:3000/check', result[0])
    .then(response => {
        if (response.status === 200) {
            return response.json();
        } else {
            throw new Error(response.status + ': Something went wrong on api server!');
        }
    }).then(response => {
        renderChecks(response)
        console.debug(response);
    }).catch(error => {
        renderError(error);
        console.error(error);
    });;
}

chrome.tabs.query({active: true}, function(tabs) {
    var tab = tabs[0];
    tab_title = tab.title;
    chrome.tabs.executeScript(tab.id, {
      code: 'var result = {"content": document.all[0].outerHTML, url: window.location.href}; result'
    }, sendRequest);
});