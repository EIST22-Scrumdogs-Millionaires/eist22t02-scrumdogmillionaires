import React from "react";
import GitHubLogo from "../images/github-logo.png";
function Footer() {
  const date = new Date();
  return (
    <footer>
      
      <div className="copyright">Copyright © {date.getFullYear()}</div>
      <div className="links">
        <a href="https://github.com/Scrumdog-Millionaries"><img src={GitHubLogo} alt="Github Logo" className="github-logo"></img></a>
      </div>
    </footer>
  );
}

export default Footer;
