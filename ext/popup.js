let changeColor = document.getElementById('changeColor');
let content = document.getElementById('changeColor');

chrome.storage.sync.get('color', function(data) {
    changeColor.style.backgroundColor = data.color;
    changeColor.setAttribute('value', data.color);
});

changeColor.onclick = function(element) {
    const request = new Request('http://localhost:3000/checks');
    fetch(request).then(response => {
        if (response.status === 200) {
            return response.json();
        } else {
            throw new Error('Something went wrong on api server!');
        }
    }).then(response => {
        let newContent = "<pre>";
        for (var key in response) {
            newContent+= key + ": " + response[key] + "<br>";
        }
        content.innerHTML = newContent + "</pre>";
        console.debug(response);
    }).catch(error => {
        console.error(error);
    });;
};