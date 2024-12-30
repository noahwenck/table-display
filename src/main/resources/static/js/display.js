const inputUrl = "http://localhost:8080/input"
const displayUrl = "http://localhost:8080"

document.addEventListener("DOMContentLoaded", function() {
    if (location.href !== displayUrl || location.href !== displayUrl.substring(0, length-1)) {
        // Add a button to return home
        let returnButton = document.createElement("button");
        returnButton.setAttribute("id", "return-button");
        returnButton.setAttribute("onclick", "location.href = displayUrl");
        returnButton.textContent = "Return home!";

        document.getElementById("navbar-top").insertBefore(
            returnButton,
            document.getElementById("action-buttons")
        );
    }
})

/**
 * Deletes the selected rows of products
 */
async function deleteProductRow() {
    console.log("Attempting to delete product(s)")

    // Gets all product Ids of selected products
    const checkedCheckboxes = document.querySelectorAll("input[type='checkbox']:checked")
    const productIdsToDelete = []
    checkedCheckboxes.forEach(box => {
        if (box.id !== "") { // Ignore select all checkbox
            productIdsToDelete.push(box.id)
        }
    })

    const response = await fetch(displayUrl + "/" + "delete/" + productIdsToDelete, {method: 'DELETE'})

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

    // Validate to only allow one selected checkbox since we are using the same checkbox system as we do with deletions
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

/**
 * IF an option is selected to search by, add a text input to search for a specific value.
 *
 * Modified from another of my projects: https://github.com/noahwenck/NodaApp/blob/master/src/main/resources/static/js/home.js#L62-L96
 */
function updateSearchDiv() {
    const option = document.getElementById('search-list').value;

    if (document.getElementById('search-param') != null) {
        const searchList = document.getElementById('search-list');
        if (searchList.value === "null") {
            // Remove added search inputs if back to default
            document.getElementById('action-buttons').removeChild(
                document.getElementById('search-param')
            );
            document.getElementById('action-buttons').removeChild(
                document.getElementById('search-button')
            );
           return;
        } else {
            // Do nothing if user clicks back on previous option
            return;
        }
    }

    let searchInput = document.createElement("input");
    searchInput.setAttribute("id", "search-param");
    searchInput.setAttribute("placeholder", "Enter the " + option + " here")

    document.getElementById("action-buttons").insertBefore(
        searchInput,
        document.getElementById("delete-button")
    );

    // Add a search button
    let searchButton = document.createElement("button");
    searchButton.setAttribute("id", "search-button");
    searchButton.setAttribute("onclick", "search()");
    searchButton.textContent = "Search!";

    document.getElementById("action-buttons").insertBefore(
        searchButton,
        document.getElementById("delete-button")
    );
}

/**
 * Sends a request to backend to get search matches
 */
function search() {
    const option = document.getElementById('search-list').value.toLowerCase();
    const searchParam = document.getElementById('search-param').value;

    if (searchParam === "") {
        alert("Please enter a " + option + " to search")
        return;
    }

    location.href = displayUrl + "/" + option + "/" + searchParam;
}