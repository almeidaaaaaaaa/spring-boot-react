import 'bootstrap/dist/css/bootstrap.min.css';
import { Container, Row, Col, NavDropdown, Form } from 'react-bootstrap';
import { FaSearch } from 'react-icons/fa';
import { FiAlignJustify } from "react-icons/fi";
import styles from './Header.module.css';

function Header({keyBoardEvent, search}) {

  const handleKeyPress = (event) => {
    if (event.key === 'Enter') {
      search();
      event.preventDefault(); 
    }
  };

  return (
    <Container fluid className={styles.header}>
      <Row>
        <Col>
          <img className={styles.logo} src="logo_weather.jpg" alt="Logo" />
        </Col>

        <Col>
          <div className={styles.searchContainer} >
            <Form.Control
              className={styles.searchBar}
              type="text"
              placeholder="Buscar cidade"
              onChange={keyBoardEvent}
              onKeyPress={handleKeyPress}
              name='search'
            />
            <FaSearch className={styles.searchIcon} />
          </div>
        </Col>

        <Col className="d-flex justify-content-center">
          <NavDropdown
            id="nav-dropdown-dark-example"
            title={<FiAlignJustify size={40} />} 
            menuVariant="dark"
            className={styles.dropdown}
          >
            <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
            <NavDropdown.Item href="#action/3.2">Another action</NavDropdown.Item>
            <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
          </NavDropdown>
        </Col>
      </Row>
    </Container>
  );
}

export default Header;
