useEffect(
  () => {
    const init = {
      method: "POST",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      }
    };

    fetch('http://localhost:8080/order', init)
      .then(response => response.json())
      .then(data => setOrders(data));
  },

  // Dependency array - only run this once when the component is initially loaded
  []
);

useEffect(
  () => {
    // Only do this if there is an `id`
    if (id) {
      const init = {
        method: "POST",
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        }
      };

      fetch(`http://localhost:8080/sighting/${{id}}`, init)
      .then((response) => {
        if (response.status !== 200) {
          return Promise.reject("sighting fetch failed");
        }
        return response.json();
      })
      .then((data) => setSighting(data))
      .catch(console.log);
    }
  },

  // Dependency array - only run this in response to any `id`
  [id]
);
