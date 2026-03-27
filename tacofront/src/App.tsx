import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import CssBaseline from '@mui/material/CssBaseline';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import Ingredientlist from './components/Ingredientlist';
const queryClient = new QueryClient();
function App() {
    return (
        <Container maxWidth="xl">
            <CssBaseline />
            <AppBar position="static">
                <Toolbar>
                    <Typography variant="h6">
                        Taco Shop
                    </Typography>
                </Toolbar>
            </AppBar>
            <QueryClientProvider client={queryClient}>
            <Ingredientlist/>
            </QueryClientProvider>
        </Container>
    );
}
export default App;