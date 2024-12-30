const inputUrl = "http://localhost:8080/input"

/**
 * Deletes the selected rows of products
 */
async function deleteProductRow() {
    console.log("Attempting to delete product(s)")

    const checkedCheckboxes = document.querySelectorAll("input[type='checkbox']:checked")
    const productIdsToDelete = []
    checkedCheckboxes.forEach(box => {
        if (box.id !== "") { // Ignore select all checkbox
            productIdsToDelete.push(box.id)
        }
    })

    const response = await fetch("http://localhost:8080/delete/" + productIdsToDelete, {method: 'DELETE'})

    if (response.ok) {
        console.log("Successfully deleted product(s).")
        location.reload()
    } else {
        console.log("Failure to delete product(s): " + response.status)
        alert("Failure to delete product(s)")
    }
}

/**
 * Sends the user to the input page to edit their selected product
 */
function editSelectedProduct() {
    const checkedCheckboxes = document.querySelectorAll("input[type='checkbox']:checked")

    // Validation since we are using the same checkboxes as we do with deletions
    if (checkedCheckboxes.length === 0) {
        alert("You must select a product to edit it.")
        return
    } else if (checkedCheckboxes.length > 1) {
        alert("You can only select one product at a time to edit it.")
        return
    }

    const productIdToEdit = checkedCheckboxes.item(0).id

    location.href = "http://localhost:8080/input/edit/" + productIdToEdit
}

/**
 * Used to toggle all checkboxes on the table
 *
 * Courtesy of: https://www.geeksforgeeks.org/how-to-add-checkbox-in-html-table/
 */
function toggle(source) {
    let checkboxes = document
        .querySelectorAll('input[type="checkbox"]');
    for (let i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i] !== source)
            checkboxes[i].checked = source.checked;
    }
}