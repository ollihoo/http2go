var currentDoc = {
    url: document.URL,
    head: document.head
};
console.log(currentDoc.head);

var start = Date.now(), end = 0;
$.ajax({
    url: "/hello",
    method: "GET"
}).done(function (msg) {
    console.log(msg);
    console.log($("#secured"));
    if (msg.isSecure)  {
        $("#secured").text(msg.isSecure);
    } else {
        $("#secured").text("undefined");
    }
    end = Date.now();
    $("#timeSpent").text(end-start);
});
