let content = document.getElementById('content');
let image = document.getElementById('stateImg');

function renderChecks(checks) {
    let newContent = "<pre>";
    for (var key in response) {
        newContent+= key + ": " + response[key] + "<br>";
    }
    content.innerHTML = newContent + "</pre>";
    image.src="done.png";
}

function renderError(error) {
    content.innerHTML = "<pre>" + error + "</pre>";
    image.src="error.png";
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
      }).then(response => response.json());
}

function onLoad() {
    image.src="loading.gif";
    postData('http://localhost:3000/check', {
        content: document.body.innerHTML,
        url: window.location.href
    })
    .then(response => {
        if (response.status === 200) {
            return response.json();
        } else {
            throw new Error('Something went wrong on api server!');
        }
    }).then(response => {
        renderChecks(response)
        console.debug(response);
    }).catch(error => {
        renderError(error);
        console.error(error);
    });;
}

onLoad();