// dateTime.js
// JavaScript code for setting the current date and time in the datetime-local input field

// Function to set current date and time
function setCurrentDateTime() {
    // Get the current date and time
    const currentDate = new Date();

    // Format the date and time as per the input format for datetime-local
    const year = currentDate.getFullYear();
    const month = String(currentDate.getMonth() + 1).padStart(2, '0');
    const day = String(currentDate.getDate()).padStart(2, '0');
    const hours = String(currentDate.getHours()).padStart(2, '0');
    const minutes = String(currentDate.getMinutes()).padStart(2, '0');

    // Set the value attribute of the input element
    const dateTimeValue = `${year}-${month}-${day}T${hours}:${minutes}`;
    document.getElementById('dateTime').value = dateTimeValue;
}

// Call the function when the page is fully loaded
window.onload = setCurrentDateTime;
