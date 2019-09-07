let image = document.getElementById('stateImg');
let checks = document.getElementById('checks');
let owners = document.getElementById('owners');
let negativeNews = document.getElementById('negativeNews');
let positiveNews = document.getElementById('positiveNews');

function mapCheckName(key) {
    let map = {
        "containsTextLinks": "Contains Text Links",
        "hasCapsOverusage": "Has Caps Overusage", 	
        "authorPresent": "Author Present",
        "areImagesLinksAvailable": "Are Images Links Available",
        "moreThanOneWeekOld" : "More Than One Week Old", 	
        "onFirstApril": "On First April",
        "inBlackList": "In Black List",
        "jokeSite": "Joke Site"
    }
    return map[key] ? map[key] : key;
}

function renderChecks(result) {
    let newContent = "<ul class=\"col-sm-6\">";
    let newContent2 = "<ul class=\"col-sm-6\">";

    let i = 0;
    for (var key in result.checkList) {
        let value = result.checkList[key];
        if(i%2 == 0){
            newContent += "<li class=\"list-group-item\"><i class=\"fa fa-check\" style=\"color: "+ (value?"red":"green") +"\"></i>" + mapCheckName(key) + "</li>";
        }else{
            newContent2 += "<li class=\"list-group-item\"><i class=\"fa fa-check\" style=\"color: "+ (value?"red":"green") +"\"></i>" + mapCheckName(key) + "</li>";
        }
        i++;
    }
    newContent += "</ul>";
    newContent2 += "</ul>";
    checks.innerHTML = newContent;
    checks2.innerHTML = newContent2;
    
    let ownersContent = "<h3></i> About:</h3>";
    for (let ownerName of result.owners) {
        ownersContent += "<li>" + ownerName + "</li>";
    }
    owners.innerHTML = ownersContent;

    let negativesContent = "";
    let imgPart = "<div class=\"thumbnail\" style=\"border:none\">"+
                    "<div class=\"col-sm-6 col-md-6 col-xs-12 image-container\">" +
                    "<img src=\"https://gdb.rferl.org/5132D96F-AADA-4F19-9A99-D9B2CB2B153B_cx0_cy5_cw0_w1023_r1_s.jpg\" style=\"height:70px; margin-left:-15px;\"/></div></div>";
    for (let news of result.negatives) {
    }
    let positivesContent = "";
    for (let news of result.positives) {

    }
    // {title, url};

}

// function renderError(error) {
//     content.innerHTML = "<pre>" + error + "</pre>";
//     image.src="error.png";
// }

// function postDataTEST(url, data = {}) {
//     return fetch(url).then(response => {
//         if (response.status === 200) {
//             return response.json();
//         } else {
//             throw new Error("Error! " + JSON.stringify(response));
//         }
//     });
// }

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
        console.debug(response);
    }).catch(error => {
        renderError(error);
        console.error(error);
    });;
}

// function addUrl() {
//     image.src="loading.gif";
//     image.style.display="block";
//     let type = document.querySelector('input[name="urltype"]:checked').value;
//     let url = document.getElementById('url');
//     if(!url.value) {
//         alert("Put url first.");
//         return;
//     }
//     let newUrl = {
//         "urlCurrent": currentUrl,
//         "urlOther": url.value,
//         "relation": "Positive" === type
//     };
//     postData('http://localhost:3000/add', newUrl).then((result)=>{
//         url.value="";
//         image.style.display="none";
//     }).catch((error)=> {
//         image.style.display="none";
//         alert(error);
//     });
// }

// button.addEventListener('click', addUrl);
chrome.tabs.query({active: true}, function(tabs) {
    var tab = tabs[0];
    tab_title = tab.title;
    chrome.tabs.executeScript(tab.id, {
      code: 'var result = { url: window.location.href}; result'
    }, sendRequest);
});
