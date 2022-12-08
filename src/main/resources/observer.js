// #docfragment "element-get", "without-license"
const element = document.getElementById('counter');
// #enddocfragment "element-get"


// #docfragment "mutation-observer"
const observer = new MutationObserver(
    function(mutations) {
        // #docfragment "on-dom-changed"
        window.java.onDomChanged(element.innerHTML);
        // #enddocfragment "on-dom-changed"
    });
// #enddocfragment "mutation-observer"


// #docfragment "track-dom-changes"
const config = {childList: true};
observer.observe(element, config);
// #enddocfragment "track-dom-changes", "without-license"