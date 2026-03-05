// ── VOTE HANDLER ──────────────────────────────────────────────────────────────

function vote(winnerId, loserId) {
    // Animate the clicked card
    const cardA = document.getElementById('cardA');
    const cardB = document.getElementById('cardB');

    // Figure out which was clicked
    const clickedWinner = (winnerId === parseInt(cardA.getAttribute('onclick').match(/\d+/)[0]))
        ? cardA : cardB;

    clickedWinner.style.transform = 'scale(0.97)';
    setTimeout(() => {
        clickedWinner.style.transform = '';

        // Submit the hidden form
        document.getElementById('winnerId').value = winnerId;
        document.getElementById('loserId').value  = loserId;
        document.getElementById('voteForm').submit();
    }, 180);
}

// ── CARD HOVER SOUND (optional visual feedback) ────────────────────────────────
document.addEventListener('DOMContentLoaded', () => {
    const cards = document.querySelectorAll('.prof-card');
    cards.forEach(card => {
        card.addEventListener('mouseenter', () => {
            card.style.transition = 'transform 0.18s cubic-bezier(0.4,0,0.2,1), border-color 0.22s, box-shadow 0.22s';
        });
    });

    // Stagger card entry animation
    cards.forEach((card, i) => {
        card.style.opacity = '0';
        card.style.transform = 'translateY(30px)';
        setTimeout(() => {
            card.style.transition = 'opacity 0.5s ease, transform 0.5s cubic-bezier(0.175,0.885,0.32,1.275)';
            card.style.opacity = '1';
            card.style.transform = 'translateY(0)';
            // Restore hover transitions after animation
            setTimeout(() => {
                card.style.transition = '';
            }, 500);
        }, 100 + i * 120);
    });
});
