document.addEventListener('DOMContentLoaded', function() {
    // Enable Alpine.js
    document.addEventListener('alpine:init', () => {
        // Any Alpine components can be initialized here
    });

    // Close alert messages when clicked
    document.querySelectorAll('[role="alert"]').forEach(alert => {
        alert.querySelector('[aria-label="Close"]').addEventListener('click', () => {
            alert.remove();
        });
    });

    // Mobile menu toggle
    const mobileMenuButton = document.getElementById('mobile-menu-button');
    const mobileMenu = document.getElementById('mobile-menu');

    if (mobileMenuButton && mobileMenu) {
        mobileMenuButton.addEventListener('click', () => {
            mobileMenu.classList.toggle('hidden');
        });
    }
});