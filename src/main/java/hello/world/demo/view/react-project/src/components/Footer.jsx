import React from "react";
import GitHubLogo from "../images/github-logo.png";
function Footer() {
  const date = new Date();
  return (
    <footer>
      <div className="copyright">Copyright © {date.getFullYear()}</div>
      <div className="links">
        <a href="https://github.com/EIST22-Scrumdogs-Millionaires"><img src={GitHubLogo} alt="Github Logo" className="github-logo"></img></a>
      </div>
      
    </footer>
  );
}

export default Footer;
