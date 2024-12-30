const displayUrl = "http://localhost:8080/"

/**
 * Makes a request to create a new product, or edit and existing one, with the inputted parameters
 */
async function addOrUpdateProduct(edit) {
    console.log("Attempting to post new product")

    if (isNaN(parseInt(document.getElementById("TypeId-input").value)) ||
        isNaN(parseInt(document.getElementById("ColorNumber-input").value))) {
        alert("Type Id and Color Number must be integers.")
        return;
    }


    // Create a JSON of a product with the new data
    const newProduct = JSON.stringify({
        "manufacturer": document.getElementById("Manufacturer-input").value,
        "typeName": document.getElementById("TypeName-input").value,
        "typeId": document.getElementById("TypeId-input").value,
        "styleName": document.getElementById("StyleName-input").value,
        "styleId": document.getElementById("StyleId-input").value,
        "colorNumber": document.getElementById("ColorNumber-input").value,
        "colorName": document.getElementById("ColorName-input").value,
        "size": document.getElementById("Size-input").value
    })

    const productId = edit ? document.getElementById("product-id").value : -1;

    const response = await fetch("http://localhost:8080/input/AddUpdate/" + productId, {
        method: 'POST',
        headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json'
        },
        body: newProduct
    })

    if (response.ok) {
        console.log("Successful response: " + response.status)
        if (edit) {
            alert("Successfully edited Product")
        } else {
            alert("Successfully added new Product")
        }

        location.href = displayUrl
    } else {
        console.log("Failure to create new Product: " + response.status)
        alert("Failure to add new Product")
    }
}