document.addEventListener("DOMContentLoaded", () => {
    // Respect user's reduced motion preferences
    const prefersReducedMotion = window.matchMedia("(prefers-reduced-motion: reduce)").matches;
    
    if (!prefersReducedMotion && typeof gsap !== 'undefined') {
        // Global Page Entrance (soft fade and slight lift)
        gsap.from("body", {
            opacity: 0,
            y: 10,
            duration: 0.6,
            ease: "power2.out"
        });

        // Stagger Cards on Dashboard and other pages
        gsap.from(".shadow-neo-raised", {
            opacity: 0,
            y: 20,
            duration: 0.8,
            stagger: 0.05,
            ease: "back.out(1.2)",
            delay: 0.1
        });

        // Stagger Sidebar Links
        gsap.from("aside a", {
            opacity: 0,
            x: -15,
            duration: 0.5,
            stagger: 0.03,
            ease: "power2.out",
            delay: 0.2
        });

        // Stagger Timeline items in Recent Activity
        gsap.from(".border-l-\\[3px\\] > div", {
            opacity: 0,
            x: 20,
            duration: 0.5,
            stagger: 0.1,
            ease: "power2.out",
            delay: 0.4
        });
        
        // Stagger Schedule Rows
        gsap.from(".bg-campus-surface > .flex-col > .flex.items-center", {
            opacity: 0,
            y: 15,
            duration: 0.6,
            stagger: 0.08,
            ease: "power2.out",
            delay: 0.3
        });
    }
});
