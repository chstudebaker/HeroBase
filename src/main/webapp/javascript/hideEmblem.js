// hideEmblem.js

// Function to hide emblem container if emblem is null or empty
function hideEmblem() {
    // Get the emblem container
    var emblemContainer = document.getElementById('emblemContainer');

    // Get the hero emblem image
    var heroEmblem = document.getElementById('heroEmblem');

    // Check if the hero emblem is null or empty
    if (!("${hero.emblem}" && "${hero.emblem}".trim())) {
        // If emblem is null or empty, hide the emblem container
        emblemContainer.style.display = 'none';
    }
}

// Execute the hideEmblem function when the DOM is fully loaded
document.addEventListener('DOMContentLoaded', hideEmblem);
