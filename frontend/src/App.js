import './App.css';
import Header from './Header';
import styles from './Header.module.css';

function App() {
  return (
    <div>
      <Header  className={styles.header}/>
    </div>
  );
}

export default App;
