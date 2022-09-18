function clearNodes(node) {
    while (node.firstChild) {
    	node.removeChild(node.firstChild);
    }
}

function resetMessage(nodeName)
{
	var messageBoxNode = document.getElementById(nodeName);
    clearNodes(messageBoxNode);
    var messageNode = document.createTextNode("");
	messageBoxNode.appendChild(messageNode);
}
	
function reportMessage(nodeName, message)
{
	var messageBoxNode = document.getElementById(nodeName);
    clearNodes(messageBoxNode);
    const messageNode = document.createElement("p");
    messageNode.innerHTML = message;
    messageBoxNode.appendChild(messageNode);
    setTimeout(function() { resetMessage(nodeName) }, 1500)
}