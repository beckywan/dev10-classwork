import { useContext, useEffect, useState } from "react";
 import Sighting from "./Sighting";
import AuthContext from "../context/AuthContext";

 function Home() {
   const [sightings, setSightings] = useState([]);

   useEffect(() => {
     fetch("http://localhost:8080/sighting")
      .then((response) => response.json())
      .then((data) => setSightings(data));
   }, []); // this will happen only once when the component is loaded

  const [userStatus] = useContext(AuthContext);

   return sightings.map((sighting) => (
    <Sighting
      key={sighting.sightingId}
      sighting={sighting}
      role={userStatus?.user.authorities}
    />
   ));
 }

export default Home;
