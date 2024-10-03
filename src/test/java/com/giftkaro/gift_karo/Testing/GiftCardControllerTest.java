/*
@SpringBootTest
@AutoConfigureMockMvc
public class GiftCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GiftCardService giftCardService;

    @Test
    public void testGetAllGiftCards() throws Exception {
        List<GiftCardDTO> giftCards = new ArrayList<>();
        giftCards.add(new GiftCardDTO(1L, "Gift Card 1", 100, 1L));
        giftCards.add(new GiftCardDTO(2L, "Gift Card 2", 200, 2L));

        // Mock the service layer
        Mockito.when(giftCardService.getAllGiftCards()).thenReturn(giftCards);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/giftcards")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Gift Card 1"));
    }

    @Test
    public void testGetGiftCardById() throws Exception {
        GiftCardDTO giftCard = new GiftCardDTO(1L, "Gift Card 1", 100, 1L);

        // Mock the service layer
        Mockito.when(giftCardService.getGiftCardById(1L)).thenReturn(giftCard);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/giftcards/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gift Card 1"));
    }

    @Test
    public void testCreateGiftCard() throws Exception {
        GiftCardDTO giftCard = new GiftCardDTO(null, "New Gift Card", 150, 1L);
        GiftCardDTO createdGiftCard = new GiftCardDTO(1L, "New Gift Card", 150, 1L);

        // Mock the service layer
        Mockito.when(giftCardService.createGiftCard(Mockito.any(GiftCardDTO.class)))
                .thenReturn(createdGiftCard);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/giftcards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"New Gift Card\", \"amount\": 150, \"restaurantId\": 1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Gift Card"));
    }
}
*/
