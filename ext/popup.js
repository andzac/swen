let content = document.getElementById('content');
let form = document.getElementById('form');
let image = document.getElementById('stateImg');
let currentUrl = "";
let button = document.getElementById('addUrl');

function mapCheckName(key) {
    let map = {
        "containsTextLinks": "Contains Text Links",
        "hasCapsOverusage": "Has Caps Overusage", 	
        "authorPresent": "Author Present",
        "areImagesLinksAvailable": "Are Images Links Available",
        "moreThanOneWeekOld" : "More Than One Week Old", 	
        "onFirstApril": "On First April",
        "inBlackList": "In Black List"
    }
    return map[key] ? map[key] : key;
}

function renderChecks(result) {
    let newContent = "<table class='pure-table pure-table-bordered' style='width:100%'>"+
    "<thead><tr><th>check</th><th>result</th></tr></thead><tbody>";
    
    for (var key in result.checkList) {
        let value = result.checkList[key];
        newContent+= "<tr><td>"+ mapCheckName(key) + "</td>";
        if(!value) {
            newContent+= "<td style='background: #afa'><img src='done.png' width='24' height='24'>";
        } else {
            newContent+= "<td style='background: red'><img src='error.png' width='24' height='24'>";
        }
        newContent+= "</td></tr>";
    }
    newContent += "</tbody></table>";
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
    image.style.display="none";
}

function renderError(error) {
    content.innerHTML = "<pre>" + error + "</pre>";
    image.src="error.png";
}
function postDataTEST(url, data = {}) {
    return fetch(url).then(response => {
        if (response.status === 200) {
            return response.json();
        } else {
            throw new Error("Error! " + JSON.stringify(response));
        }
    });
}
function postData(url, data = {}) {
    return fetch(url, {
        method: 'POST',
        cache: 'no-cache',
        headers: {
              'Content-Type': 'application/json',
        },
        redirect: 'follow',
        credentials: 'omit',
        referrer: 'no-referrer',
        credentials: 'same-origin',
        body: JSON.stringify(data),
    }).then(function(response) {
        if (response.status === 200) {
            return response.json();
        } else {
            throw new Error("Error! " + JSON.stringify(response));
        }
    }, (error) => { throw error;});
}

function sendRequest(result) {
    image.src="loading.gif";
    image.style.display = "block";
    currentUrl = result[0].url;
    postData('http://localhost:3000/check', result[0])
    .then(response => {
        renderChecks(response);
        form.style.display = "block";
        console.debug(response);
    }).catch(error => {
        renderError(error);
        console.error(error);
    });;
}

function addUrl() {
    image.src="loading.gif";
    image.style.display="block";
    let type = document.querySelector('input[name="urltype"]:checked').value;
    let url = document.getElementById('url');
    if(!url.value) {
        alert("Put url first.");
        return;
    }
    let newUrl = {
        "urlCurrent": currentUrl,
        "urlOther": url.value,
        "relation": "Positive" === type
    };
    postData('http://localhost:3000/add', newUrl).then((result)=>{
        url.value="";
        image.style.display="none";
    }).catch((error)=> {
        image.style.display="none";
        alert(error);
    });
}

button.addEventListener('click', addUrl);
chrome.tabs.query({active: true}, function(tabs) {
    var tab = tabs[0];
    tab_title = tab.title;
    chrome.tabs.executeScript(tab.id, {
      code: 'var result = { url: window.location.href}; result'
    }, sendRequest);
});