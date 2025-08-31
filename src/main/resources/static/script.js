const searchBox = document.querySelector('.field'); 
const suggestionBox = document.querySelector('.suggestion-div');

searchBox.addEventListener('input', async () => {
    let value = searchBox.value.trim();
    if (value.length === 0) {
        suggestionBox.innerHTML = "";
        return;
    }

    let response = await fetch(`http://localhost:8080/api/product?query=${value}`);
    let products = await response.json();

    suggestionBox.innerHTML = products
        .map(p => `<div class="suggestion-item">${p.name}</div>`)
        .join("");

    document.querySelectorAll('.suggestion-item').forEach((item) => {
        item.addEventListener('click', () => {
            searchBox.value = item.textContent;
            suggestionBox.innerHTML = "";
        });
    });
});
