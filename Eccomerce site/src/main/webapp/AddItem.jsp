// Simple JavaScript for Add Item page
function validateItemForm() {
    var itemName = document.getElementById("itemName").value;
    var price = document.getElementById("price").value;
    
    if(itemName.trim() === "") {
        alert("Please enter item name");
        return false;
    }
    
    if(price.trim() === "" || isNaN(price) || parseFloat(price) <= 0) {
        alert("Please enter a valid price");
        return false;
    }
    
    return true;
}

function clearForm() {
    document.getElementById("itemName").value = "";
    document.getElementById("description").value = "";
    document.getElementById("price").value = "";
    alert("Form cleared!");
}