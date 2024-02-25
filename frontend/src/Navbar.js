import React from 'react';
import { Link } from 'react-router-dom';

function Navbar() {
  return (
    <nav>
      <ul>
        <li>
          <Link to="/">Home</Link>
        </li>
        <li>
          <Link to="/dog-facts">Dog Facts</Link>
        </li>
        {/* Add more links as you create more pages */}
      </ul>
    </nav>
  );
}

export default Navbar;
