
//event for submit button
document.querySelector(".search-button").addEventListener("click", () => {
  const item= document.querySelector(".product-search").value;
  console.log(item);
});



const url="https://localhost:8080/api/proudcts"

 async function fetchProducts() {
    const response=await fetch(url)
    .json()
}
